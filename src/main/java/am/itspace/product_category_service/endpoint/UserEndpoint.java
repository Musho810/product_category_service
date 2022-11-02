package am.itspace.product_category_service.endpoint;


import am.itspace.product_category_service.dto.CreateUserDto;
import am.itspace.product_category_service.dto.UserAuthDto;
import am.itspace.product_category_service.dto.UserAuthResponseDto;
import am.itspace.product_category_service.mapper.UserMapper;
import am.itspace.product_category_service.model.Role;
import am.itspace.product_category_service.model.User;
import am.itspace.product_category_service.repository.UserRepository;
import am.itspace.product_category_service.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserEndpoint {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
        Optional<User> existingUser = userRepository.findByEmail(createUserDto.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = userMapper.map(createUserDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userMapper.map((userRepository.save(user))));
    }

    @PostMapping("/user/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto) {
        Optional<User> byEmail = userRepository.findByEmail(userAuthDto.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(UserAuthResponseDto.builder()
                        .token(jwtTokenUtil.generateToken(user.getEmail()))
                        .user(userMapper.map(user))
                        .build()
                );
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}

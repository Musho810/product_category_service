package am.itspace.product_category_service.service;

import am.itspace.product_category_service.model.User;
import am.itspace.product_category_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}

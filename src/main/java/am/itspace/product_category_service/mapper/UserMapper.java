package am.itspace.product_category_service.mapper;

import am.itspace.product_category_service.dto.CreateUserDto;
import am.itspace.product_category_service.dto.UserDto;
import am.itspace.product_category_service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(CreateUserDto createUserDto);
    UserDto map(User user);


}

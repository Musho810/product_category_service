package am.itspace.product_category_service.repository;

import am.itspace.product_category_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);

}

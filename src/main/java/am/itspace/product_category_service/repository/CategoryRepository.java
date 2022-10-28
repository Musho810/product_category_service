package am.itspace.product_category_service.repository;

import am.itspace.product_category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

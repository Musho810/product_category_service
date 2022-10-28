package am.itspace.product_category_service.repository;

import am.itspace.product_category_service.dto.ProductResponseDto;
import am.itspace.product_category_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<List<ProductResponseDto>> findByCategoryId(int id);
}


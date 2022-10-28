package am.itspace.product_category_service.service;

import am.itspace.product_category_service.dto.ProductResponseDto;
import am.itspace.product_category_service.mapper.ProductMapper;
import am.itspace.product_category_service.model.Product;
import am.itspace.product_category_service.repository.ProductRepository;
import com.datical.liquibase.ext.checks.config.model.AbstractConfigurableRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponseDto> getAllProducts() {
        return productMapper.map(productRepository.findAll());
    }
       public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public Optional<List<ProductResponseDto>> findByCategoryId(int id) {
        return productRepository.findByCategoryId(id);
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }
}

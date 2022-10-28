package am.itspace.product_category_service.endpoint;


import am.itspace.product_category_service.dto.ProductResponseDto;
import am.itspace.product_category_service.mapper.ProductMapper;
import am.itspace.product_category_service.model.Product;
import am.itspace.product_category_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductEndpoint {
    private final ProductService productService;


    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id,Product product) {
        if (productService.findById(id).get().getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        if (id == 0) {
            return ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/{categoryId}")
    public ResponseEntity<List<ProductResponseDto>> getByCategoryId(@PathVariable("categoryId") int id) {
        Optional<List<ProductResponseDto>> byCategoryId = productService.findByCategoryId(id);
        if (byCategoryId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byCategoryId.get());
    }
}

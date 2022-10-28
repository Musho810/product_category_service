package am.itspace.product_category_service.endpoint;


import am.itspace.product_category_service.model.Category;
import am.itspace.product_category_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryEndpoint {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategorys() {
        return categoryService.getAllCategorys();
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createProduct(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/categories")
    public ResponseEntity<?> updateProduct(@RequestBody Category category) {
        if (category.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        if (id == 0) {
            return ResponseEntity.badRequest().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<Category> getProductById(@RequestBody Category category) {
        Optional<Category> byId = categoryService.findById(category.getId());
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }
}

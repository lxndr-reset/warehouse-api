package ru.yourwarehouse.warehouseapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yourwarehouse.warehouseapi.entity.Category;
import ru.yourwarehouse.warehouseapi.service.CategoryService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public Optional<Category> get(@PathVariable UUID id) {
        return categoryService.get(id);
    }

    @PostMapping
    public Optional<Category> save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        categoryService.delete(id);
    }

    @DeleteMapping
    public void delete(@RequestBody Category category) {
        categoryService.delete(category);
    }
}

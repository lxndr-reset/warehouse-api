package ru.yourwarehouse.warehouseapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourwarehouse.warehouseapi.entity.Category;
import ru.yourwarehouse.warehouseapi.repository.CategoryRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> get(UUID id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> save(Category category) {
        return Optional.of(categoryRepository.save(category));
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void delete(UUID uuid) {
        categoryRepository.deleteById(uuid);
    }

    public Optional<Category> get(String name) {
        return categoryRepository.findCategoriesByName(name);
    }
}

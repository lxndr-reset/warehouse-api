package ru.yourwarehouse.warehouseapi.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import ru.yourwarehouse.warehouseapi.dto.ItemDTO;
import ru.yourwarehouse.warehouseapi.entity.Category;
import ru.yourwarehouse.warehouseapi.entity.Item;
import ru.yourwarehouse.warehouseapi.service.CategoryService;
import ru.yourwarehouse.warehouseapi.service.ItemService;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public Optional<Item> get(@PathVariable UUID id) {
        return itemService.get(id);
    }

    @PostMapping
    public Optional<Item> save(@RequestBody ItemDTO dto) {
        Item item = createItemFromDTO(dto);
        return itemService.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        itemService.delete(id);
    }

    @DeleteMapping
    public void delete(@RequestBody ItemDTO dto) {
        Item item = createItemFromDTO(dto);
        itemService.delete(item);
    }

    @SneakyThrows
    private Item createItemFromDTO(ItemDTO dto) {
        CompletableFuture<Category> categoryFuture = CompletableFuture
                .supplyAsync(() -> categoryService.get(dto.getCategory()).get());

        Item item;

        if (dto.getArticle() != null) {
            item = itemService.get(dto.getArticle()).get();
        } else {
            item = new Item();
        }

        item.setArticle(dto.getArticle());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(categoryFuture.get());
        item.setCount(dto.getCount());

        return item;
    }

}

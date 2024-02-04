package ru.yourwarehouse.warehouseapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yourwarehouse.warehouseapi.entity.Item;
import ru.yourwarehouse.warehouseapi.service.ItemService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouse")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public Optional<Item> getItem(@PathVariable UUID id) {
        return itemService.get(id);
    }

    @PostMapping
    public Optional<Item> createItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    @PostMapping
    public Optional<Item> editItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable UUID id) {
        itemService.delete(id);
    }

    @DeleteMapping
    public void deleteItem(@RequestBody Item item) {
        itemService.delete(item);
    }
}

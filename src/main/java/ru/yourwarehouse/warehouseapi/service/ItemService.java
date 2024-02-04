package ru.yourwarehouse.warehouseapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourwarehouse.warehouseapi.entity.Item;
import ru.yourwarehouse.warehouseapi.repository.ItemRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Optional<Item> get(UUID id) {
        return itemRepository.findById(id);
    }

    public Optional<Item> save(Item item) {
        item.setLastEditionDateNow();

        return Optional.of(itemRepository.save(item));
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }
    public void delete(UUID uuid) {
        itemRepository.deleteById(uuid);
    }
}

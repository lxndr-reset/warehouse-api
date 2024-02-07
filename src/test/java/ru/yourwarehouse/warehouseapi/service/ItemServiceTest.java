package ru.yourwarehouse.warehouseapi.service;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yourwarehouse.warehouseapi.entity.Category;
import ru.yourwarehouse.warehouseapi.entity.Item;
import ru.yourwarehouse.warehouseapi.repository.ItemRepository;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    final Category correctCategory = new Category(UUID.randomUUID(), "correct");

    final Category incorrectCategory = new Category(UUID.randomUUID(), "incorrect");

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @BeforeAll
    static void beforeAll() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()){
            validator = validatorFactory.getValidator();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Validator validator;

    @Test
    void giveItem_whenAllValuesAreValid_thenAdd() {
        LocalDateTime biggerTimeThanCurrent = LocalDateTime.now().plus(Period.ofDays(1));
        UUID article = UUID.randomUUID();
        Item item = new Item(article, "test name", "test description", correctCategory, 2, biggerTimeThanCurrent, LocalDateTime.now());

        Mockito.when(itemRepository.save(item)).thenReturn(item);
        itemService.save(item);
        verify(itemRepository, Mockito.atMostOnce()).save(item);

        assertTrue(item.getLastEditionDate().isBefore(biggerTimeThanCurrent));
        assertEquals(item.getArticle(), article);
    }

    @Test
    void giveItem_whenNameIsEmpty_thenViolation() {
        Item item = new Item(UUID.randomUUID(), "", "test description", correctCategory, 2, LocalDateTime.now(), LocalDateTime.now());

        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertFalse(violations.isEmpty(), "There should be at least one violation");
        assertEquals("size must be between 2 and 64", violations.iterator().next().getMessage());
    }

    @Test
    void giveItem_whenCountIsNegative_thenViolation(){
        Item item = new Item(UUID.randomUUID(), "test name", "test description", correctCategory, -1, LocalDateTime.now(), LocalDateTime.now());

        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertFalse(violations.isEmpty(), "There should be at least one violation");
        assertEquals("must be greater than or equal to 0", violations.iterator().next().getMessage());
    }
}

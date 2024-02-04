package ru.yourwarehouse.warehouseapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemDTO {

    private UUID article;

    @NotNull
    private String name;

    @Size(max = 640)
    private String description;

    @NotNull
    private String category;

    @PositiveOrZero
    private int count;
}

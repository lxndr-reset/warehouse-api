package ru.yourwarehouse.warehouseapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity @Table(name = "\"category\"")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder
@EqualsAndHashCode
@ToString
public class Category {

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotEmpty
    private UUID id;

    @Size(min = 2, max = 100)
    @Column(name = "name")
    private String categoryName;
}

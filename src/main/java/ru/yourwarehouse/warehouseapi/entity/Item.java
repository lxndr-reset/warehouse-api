package ru.yourwarehouse.warehouseapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Getter @ToString
@Entity @Table(name = "\"item\"")
@EqualsAndHashCode
@AllArgsConstructor
public class Item {

    @Id
    @NotNull @Setter
    @Column(name = "article")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID article;

    @Size(min = 2, max = 64)
    @Column(name = "name") @Setter
    private String name;

    @Column(name = "description")
    @Setter @Size(max = 640)
    private String description;

    @NotNull @Setter
    @JoinColumn(name = "category", referencedColumnName = "name")
    @ManyToOne(fetch = LAZY, cascade = {DETACH, MERGE, PERSIST, REFRESH})
    private Category category;

    @Column(name = "count")
    @PositiveOrZero @Setter
    private int count;

    @PastOrPresent @NotNull
    @Column(name = "edition_date")
    private LocalDateTime lastEditionDate;

    @PastOrPresent @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Item() {
        if (creationDate == null){
            creationDate = LocalDateTime.now();
        }
    }

    public void setLastEditionDateNow() {
        lastEditionDate = LocalDateTime.now();
    }
}

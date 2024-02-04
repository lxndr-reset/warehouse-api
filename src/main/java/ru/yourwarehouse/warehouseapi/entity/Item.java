package ru.yourwarehouse.warehouseapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity @Table(name = "\"item\"")
@Getter @ToString @Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "article")
    @Setter
    @NotEmpty
    private UUID article;

    @Size(min = 2, max = 64) @Column(name = "name")
    @Setter @NotEmpty
    private String name;

    @Size(max = 640) @Column(name = "description")
    @Setter
    private String description;

    @ManyToOne(fetch = LAZY, cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "category", referencedColumnName = "name")
    @NotEmpty @Setter
    private Category category;

    @PositiveOrZero @Column(name = "count")
    @Setter
    private int count;

    @PastOrPresent @NotNull
    @Column(name = "edition_date")
    private LocalDateTime lastEditionDate;

    @PastOrPresent @NotNull
    @Column(name = "creation_date")
    private final LocalDateTime creationDate;

    public Item() {
        creationDate = LocalDateTime.now();
    }

    public void setLastEditionDateNow() {
        lastEditionDate = LocalDateTime.now();
    }
}

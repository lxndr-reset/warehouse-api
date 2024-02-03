package ru.yourwarehouse.warehouseapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "\"item\"")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "article")
    private UUID article;

    @NotEmpty
    @Size(min = 2, max = 64)
    @Column(name = "name")
    private String name;

    @Size(max = 640)
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = LAZY, cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "category", referencedColumnName = "name")
    private Category category;

    @PositiveOrZero
    @Column(name = "count")
    private int count;

    @PastOrPresent
    @Column(name = "edition_date")
    private Timestamp lastChangesDate;

    @PastOrPresent
    @Column(name = "creation_date")
    private final Timestamp creationDate;

    public Item() {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return getCount() == item.getCount() && Objects.equals(getArticle(), item.getArticle()) && Objects.equals(getName(), item.getName()) && Objects.equals(getDescription(), item.getDescription()) && Objects.equals(getCategory(), item.getCategory()) && Objects.equals(getLastChangesDate(), item.getLastChangesDate()) && Objects.equals(getCreationDate(), item.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArticle(), getName(), getDescription(), getCategory(), getCount(), getLastChangesDate(), getCreationDate());
    }

    public void setArticle(UUID article) {
        this.article = article;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCount(int amount) {
        this.count = amount;
    }

    public void setLastChangesDate(Timestamp lastChangesDate) {
        this.lastChangesDate = lastChangesDate;
    }

    public UUID getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public int getCount() {
        return count;
    }

    public Timestamp getLastChangesDate() {
        return lastChangesDate;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }
}

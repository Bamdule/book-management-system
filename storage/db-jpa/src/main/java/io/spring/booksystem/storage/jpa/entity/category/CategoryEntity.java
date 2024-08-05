package io.spring.booksystem.storage.jpa.entity.category;

import io.spring.booksystem.domain.category.CategoryType;
import io.spring.booksystem.domain.category.Category;
import io.spring.booksystem.storage.jpa.entity.common.BaseModelEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "category")
@Entity
public class CategoryEntity extends BaseModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CategoryType type;

    protected CategoryEntity() {
    }

    public CategoryEntity(CategoryType type) {
        this.type = type;
    }

    public static CategoryEntity of(Category category) {
        return new CategoryEntity(category.getType());
    }

    public Category toModel() {
        return Category.of(this.id, this.type);
    }
}

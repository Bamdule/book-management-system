package io.spring.booksystem.domain.category;

import lombok.Getter;

@Getter
public class Category {

    private Long id;
    private CategoryType type;

    private Category(Long id, CategoryType type) {
        this.id = id;
        this.type = type;
    }

    public static Category of(Long id, CategoryType type) {
        return new Category(id, type);
    }
}

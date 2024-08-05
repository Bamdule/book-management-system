package io.spring.booksystem.domain.category;

import lombok.Getter;

@Getter
public enum CategoryType {
    LITERATURE("문학"),
    ECONOMIC_MANAGEMENT("경제경영"),
    HUMANITIES("인문학"),
    IT("Information Technology"),
    SCIENCE("과학"),
    OCCULT("오컬트"),
    SURREAL("초현실"),
    FICTION("소설");

    private final String description;

    CategoryType(String description) {
        this.description = description;
    }

    public boolean equals(CategoryType categoryType) {
        return this == categoryType;
    }
}

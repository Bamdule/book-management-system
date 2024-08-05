package io.spring.booksystem.application.usecase.book.categoryupdate;

import io.spring.booksystem.domain.category.CategoryType;

import java.util.List;

public record BookCategoryUpdateCommand(
    Long id,
    List<CategoryType> categoryTypes
) {
}

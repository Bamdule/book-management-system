package io.spring.booksystem.application.usecase.book.search;

import io.spring.booksystem.domain.book.BookStatus;
import io.spring.booksystem.domain.category.CategoryType;

import java.util.List;

public record BookSearchQuery(
    String title, String author, BookStatus status, List<CategoryType> bookCategoryTypes
) {
}

package io.spring.booksystem.presentation.book.dto;

import io.spring.booksystem.application.usecase.book.search.BookSearchQuery;
import io.spring.booksystem.domain.book.BookStatus;
import io.spring.booksystem.domain.category.CategoryType;

import java.util.List;

public record BookSearchRequestDto(String title, String author, BookStatus status, List<CategoryType> categoryTypes
) {

    public BookSearchQuery to() {
        return new BookSearchQuery(title, author, status, categoryTypes);
    }
}

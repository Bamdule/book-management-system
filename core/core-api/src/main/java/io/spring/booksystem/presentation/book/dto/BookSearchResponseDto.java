package io.spring.booksystem.presentation.book.dto;

import io.spring.booksystem.application.usecase.book.BookInfo;
import io.spring.booksystem.domain.book.BookStatus;
import io.spring.booksystem.domain.category.CategoryType;

import java.util.List;

public record BookSearchResponseDto(
    Long id, String title, String author, BookStatus status, boolean isRentalAvailability,
    List<CategoryType> categoryTypes
) {


    public static BookSearchResponseDto of(BookInfo bookInfo) {
        return new BookSearchResponseDto(
            bookInfo.id(),
            bookInfo.title(),
            bookInfo.author(),
            bookInfo.status(),
            bookInfo.isRentalAvailability(),
            bookInfo.categoryTypes()
        );
    }
}

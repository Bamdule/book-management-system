package io.spring.booksystem.application.usecase.book;

import io.spring.booksystem.domain.book.Book;
import io.spring.booksystem.domain.book.BookStatus;
import io.spring.booksystem.domain.category.CategoryType;

import java.util.List;

public record BookInfo(
    Long id,
    String title,
    String author,
    BookStatus status,
    boolean isRentalAvailability,
    List<CategoryType> categoryTypes
) {
    public static BookInfo of(Book book) {
        return new BookInfo(book.getId(), book.getTitle(), book.getAuthor(), book.getStatus(), book.isRentalAvailability(), book.getBookCategories().categoryTypes());
    }
}

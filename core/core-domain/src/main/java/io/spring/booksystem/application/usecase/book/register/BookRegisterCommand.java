package io.spring.booksystem.application.usecase.book.register;

import io.spring.booksystem.domain.book.Book;
import io.spring.booksystem.domain.book.BookCategories;
import io.spring.booksystem.domain.category.CategoryType;

import java.util.List;

public record BookRegisterCommand(
    String title,
    String author,
    List<CategoryType> categoryTypes
) {

    public Book to() {
        return Book.create(
            title,
            author,
            new BookCategories(categoryTypes)
        );
    }
}

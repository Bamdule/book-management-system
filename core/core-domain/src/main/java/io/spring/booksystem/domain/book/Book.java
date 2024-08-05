package io.spring.booksystem.domain.book;

import io.spring.booksystem.domain.book.exception.BookAuthorRequiredException;
import io.spring.booksystem.domain.book.exception.BookCategoriesRequiredException;
import io.spring.booksystem.domain.book.exception.BookTitleRequiredException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
public class Book {

    private Long id;

    private String title;

    private String author;

    private BookStatus status;

    private BookCategories bookCategories;

    private Book(Long id, String title, String author, BookStatus status, BookCategories bookCategories) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.bookCategories = bookCategories;
    }

    public static Book create(String title, String author, BookCategories bookCategories) {
        if (bookCategories == null || bookCategories.isEmpty()) {
            throw new BookCategoriesRequiredException();
        }

        if (title == null || title.isEmpty()) {
            throw new BookTitleRequiredException();
        }

        if (author == null || author.isEmpty()) {
            throw new BookAuthorRequiredException();
        }

        return new Book(null, title, author, BookStatus.NORMAL, bookCategories);
    }

    public static Book of(Long id, String title, String author, BookStatus status, BookCategories bookCategories) {
        return new Book(id, title, author, status, bookCategories);
    }

    public boolean isRentalAvailability() {
        return this.status == BookStatus.NORMAL;
    }

}

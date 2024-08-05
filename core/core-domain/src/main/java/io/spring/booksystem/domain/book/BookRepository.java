package io.spring.booksystem.domain.book;

import io.spring.booksystem.application.usecase.book.search.BookSearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookRepository {
    Book register(Book book);

    Page<Book> findAll(BookSearchQuery bookSearchQuery, Pageable pageable);

    Optional<Book> findById(Long id);

    void addBookCategories(Long bookId, BookCategories bookCategories);

    void removeBookCategories(Long bookId, BookCategories bookCategories);
}

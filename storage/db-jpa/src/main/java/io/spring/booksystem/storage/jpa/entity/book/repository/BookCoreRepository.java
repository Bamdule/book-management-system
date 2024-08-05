package io.spring.booksystem.storage.jpa.entity.book.repository;

import io.spring.booksystem.application.usecase.book.search.BookSearchQuery;
import io.spring.booksystem.domain.book.Book;
import io.spring.booksystem.domain.book.BookCategories;
import io.spring.booksystem.domain.book.BookRepository;
import io.spring.booksystem.domain.book.exception.BookNotFoundException;
import io.spring.booksystem.domain.common.exception.ExceptionCode;
import io.spring.booksystem.storage.jpa.entity.book.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookCoreRepository implements BookRepository {

    private final BookJpaRepository bookJpaRepository;

    @Override
    public Book register(Book book) {
        return bookJpaRepository.save(BookEntity.create(book)).to();
    }

    @Override
    public Page<Book> findAll(BookSearchQuery bookSearchQuery, Pageable pageable) {
        return bookJpaRepository.findAllByQuery(bookSearchQuery, pageable)
            .map(BookEntity::to);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookJpaRepository.findById(id).map(BookEntity::to);
    }

    @Override
    public void addBookCategories(Long bookId, BookCategories bookCategories) {
        BookEntity bookEntity = bookJpaRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException(ExceptionCode.BOOK_NOT_FOUND));

        bookCategories
            .categoryTypes()
            .forEach(bookEntity::addCategory);
    }

    @Override
    public void removeBookCategories(Long bookId, BookCategories bookCategories) {
        BookEntity bookEntity = bookJpaRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException(ExceptionCode.BOOK_NOT_FOUND));

        bookCategories
            .categoryTypes()
            .forEach(bookEntity::removeCategory);
    }

}

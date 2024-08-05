package io.spring.booksystem.application.usecase.book.categoryupdate;

import io.spring.booksystem.domain.book.Book;
import io.spring.booksystem.domain.book.BookCategories;
import io.spring.booksystem.domain.book.BookRepository;
import io.spring.booksystem.domain.book.exception.BookCategoriesRequiredException;
import io.spring.booksystem.domain.book.exception.BookNotFoundException;
import io.spring.booksystem.domain.common.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookCategoryUpdateUseCase {

    private final BookRepository bookRepository;

    @Transactional
    public void updateBookCategory(BookCategoryUpdateCommand command) {
        if (command.categoryTypes() == null || command.categoryTypes().isEmpty()) {
            throw new BookCategoriesRequiredException();
        }

        Book book = bookRepository.findById(command.id())
            .orElseThrow(() -> new BookNotFoundException(ExceptionCode.BOOK_NOT_FOUND));

        BookCategories bookCategories = book.getBookCategories();

        BookCategories addCategories = bookCategories.createAddBookCategories(command.categoryTypes());
        BookCategories removeCategories = bookCategories.createRemoveBookCategories(command.categoryTypes());

        bookRepository.addBookCategories(book.getId(), addCategories);
        bookRepository.removeBookCategories(book.getId(), removeCategories);
    }
}

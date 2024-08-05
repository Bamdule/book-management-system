package io.spring.booksystem.application.usecase.book.register;

import io.spring.booksystem.application.usecase.book.BookInfo;
import io.spring.booksystem.domain.book.Book;
import io.spring.booksystem.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookRegisterUseCase {

    private final BookRepository bookRepository;

    @Transactional
    public BookInfo register(BookRegisterCommand command) {
        Book book = command.to();
        return BookInfo.of(bookRepository.register(book));
    }
}

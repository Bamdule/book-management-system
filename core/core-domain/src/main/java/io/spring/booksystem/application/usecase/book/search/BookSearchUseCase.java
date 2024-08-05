package io.spring.booksystem.application.usecase.book.search;

import io.spring.booksystem.application.usecase.book.BookInfo;
import io.spring.booksystem.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookSearchUseCase {

    private final BookRepository bookRepository;

    public Page<BookInfo> search(BookSearchQuery query, Pageable pageable) {
        return bookRepository.findAll(query, pageable)
            .map(BookInfo::of);
    }
}

package io.spring.booksystem.storage.jpa.entity.book.repository;

import io.spring.booksystem.application.usecase.book.search.BookSearchQuery;
import io.spring.booksystem.storage.jpa.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookQueryDslRepository {
    Page<BookEntity> findAllByQuery(BookSearchQuery query, Pageable pageable);
}

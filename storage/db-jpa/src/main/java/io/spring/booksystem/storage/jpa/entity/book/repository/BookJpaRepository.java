package io.spring.booksystem.storage.jpa.entity.book.repository;

import io.spring.booksystem.storage.jpa.entity.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long>, BookQueryDslRepository {
}

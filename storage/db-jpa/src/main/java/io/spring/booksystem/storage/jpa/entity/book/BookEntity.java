package io.spring.booksystem.storage.jpa.entity.book;

import io.spring.booksystem.domain.book.Book;
import io.spring.booksystem.domain.book.BookCategories;
import io.spring.booksystem.domain.book.BookStatus;
import io.spring.booksystem.domain.category.CategoryType;
import io.spring.booksystem.storage.jpa.entity.common.BaseModelEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "book")
@Entity
public class BookEntity extends BaseModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookStatus status;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<BookCategoryEntity> bookCategories = new ArrayList<>();

    protected BookEntity() {
    }

    private BookEntity(Long id, String title, String author, BookStatus status, BookCategories bookCategories) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.bookCategories = bookCategories.categoryTypes()
            .stream()
            .map(categoryType -> BookCategoryEntity.create(categoryType, this))
            .toList();
    }

    public void addCategory(CategoryType categoryType) {
        bookCategories.add(BookCategoryEntity.create(categoryType, this));
    }

    public void removeCategory(CategoryType categoryType) {
        bookCategories.removeIf(bookCategoryEntity -> categoryType.equals(bookCategoryEntity.getCategoryType()));
    }

    public Book to() {
        return Book.of(
            this.id,
            this.title,
            this.author,
            this.status,
            new BookCategories(bookCategories.stream().map(BookCategoryEntity::getCategoryType).collect(Collectors.toList()))
        );
    }

    public static BookEntity create(Book book) {
        BookEntity bookEntity = new BookEntity(
            null,
            book.getTitle(),
            book.getAuthor(),
            book.getStatus(),
            book.getBookCategories()
        );

        return bookEntity;
    }
}

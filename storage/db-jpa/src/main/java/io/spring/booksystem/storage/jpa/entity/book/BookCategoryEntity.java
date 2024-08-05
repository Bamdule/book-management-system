package io.spring.booksystem.storage.jpa.entity.book;

import io.spring.booksystem.domain.category.CategoryType;
import io.spring.booksystem.storage.jpa.entity.common.BaseModelEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "book_category")
@Entity
public class BookCategoryEntity extends BaseModelEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_category_type")
    private CategoryType categoryType;


    protected BookCategoryEntity() {
    }

    private BookCategoryEntity(Long id, BookEntity book, CategoryType categoryType) {
        this.book = book;
        this.categoryType = categoryType;
    }

    public static BookCategoryEntity of(Long id, CategoryType categoryType, BookEntity book) {
        return new BookCategoryEntity(id, book, categoryType);
    }

    public static BookCategoryEntity create(CategoryType categoryType, BookEntity book) {
        return new BookCategoryEntity(null, book, categoryType);
    }

//    public BookCategory to() {
//        return BookCategory.of(this.id, this.categoryType);
//    }
}

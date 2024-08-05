package io.spring.booksystem.domain.book;

import io.spring.booksystem.domain.book.exception.BookCategoriesRequiredException;
import io.spring.booksystem.domain.category.CategoryType;

import java.util.List;

public record BookCategories(List<CategoryType> categoryTypes) {

    public BookCategories {
        if (categoryTypes == null) {
            throw new BookCategoriesRequiredException();
        }
    }


    public boolean isEmpty() {
        return this.categoryTypes.isEmpty();
    }

    public int size() {
        return this.categoryTypes.size();
    }

    public BookCategories createAddBookCategories(List<CategoryType> updatedCategoryTypes) {
        return new BookCategories(updatedCategoryTypes.stream()
            .filter(this::noneMatch)
            .toList());
    }

    public BookCategories createRemoveBookCategories(List<CategoryType> updatedCategoryTypes) {
        return new BookCategories(this.categoryTypes.stream()
            .filter(categoryType -> updatedCategoryTypes.stream().noneMatch(categoryType::equals))
            .toList());
    }

    private boolean noneMatch(CategoryType categoryType) {
        return this.categoryTypes.stream()
            .noneMatch(bookCategory -> bookCategory.equals(categoryType));
    }
}


package io.spring.booksystem.presentation.book.dto;

import io.spring.booksystem.application.usecase.book.categoryupdate.BookCategoryUpdateCommand;
import io.spring.booksystem.domain.category.CategoryType;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BookCategoryUpdateRequestDto(
    @NotNull(message = "도서 카테고리 타입은 필수 값입니다.")
    List<CategoryType> categoryTypes
) {

    public BookCategoryUpdateCommand to(Long bookID) {
        return new BookCategoryUpdateCommand(bookID, categoryTypes);
    }
}

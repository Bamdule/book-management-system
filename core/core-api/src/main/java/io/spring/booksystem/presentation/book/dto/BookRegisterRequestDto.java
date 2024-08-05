package io.spring.booksystem.presentation.book.dto;

import io.spring.booksystem.application.usecase.book.register.BookRegisterCommand;
import io.spring.booksystem.domain.category.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record BookRegisterRequestDto(
    @NotBlank(message = "도서 제목은 필수 값입니다.")
    String title,

    @NotBlank(message = "도서 저자는 필수 값입니다.")
    String author,
    @Size(min = 1, message = "도서 카테고리는 최소 한개 이상 입력해야 합니다.")
    @NotNull(message = "도서 카테고리는 필수 값입니다.")
    List<CategoryType> categoryTypes
) {

    public BookRegisterCommand to() {
        return new BookRegisterCommand(title, author, categoryTypes);
    }
}

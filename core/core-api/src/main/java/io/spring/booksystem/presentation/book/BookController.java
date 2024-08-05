package io.spring.booksystem.presentation.book;

import io.spring.booksystem.application.usecase.book.categoryupdate.BookCategoryUpdateUseCase;
import io.spring.booksystem.application.usecase.book.register.BookRegisterUseCase;
import io.spring.booksystem.application.usecase.book.search.BookSearchUseCase;
import io.spring.booksystem.presentation.book.dto.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "도서 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookController {

    private final BookSearchUseCase bookSearchUseCase;
    private final BookCategoryUpdateUseCase bookCategoryUpdateUseCase;
    private final BookRegisterUseCase bookRegisterUseCase;

    @GetMapping
    public ResponseEntity<Page<BookSearchResponseDto>> findAllByQuery(
        BookSearchRequestDto request,
        @PageableDefault Pageable pageable
    ) {

        Page<BookSearchResponseDto> responses = bookSearchUseCase.search(request.to(), pageable)
            .map(BookSearchResponseDto::of);

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<BookRegisterResponseDto> registerBook(@RequestBody @Valid BookRegisterRequestDto request) {
        BookRegisterResponseDto response = BookRegisterResponseDto.of(bookRegisterUseCase.register(request.to()));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{bookId}/category-types")
    public ResponseEntity<Void> updateCategoryTypes(
        @PathVariable("bookId") Long bookId,
        @RequestBody @Valid BookCategoryUpdateRequestDto request
    ) {
        bookCategoryUpdateUseCase.updateBookCategory(request.to(bookId));
        return ResponseEntity.noContent().build();
    }
}

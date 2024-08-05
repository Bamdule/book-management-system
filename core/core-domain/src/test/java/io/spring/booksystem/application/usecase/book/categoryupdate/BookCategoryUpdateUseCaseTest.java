package io.spring.booksystem.application.usecase.book.categoryupdate;

import io.spring.booksystem.domain.book.Book;
import io.spring.booksystem.domain.book.BookCategories;
import io.spring.booksystem.domain.book.BookRepository;
import io.spring.booksystem.domain.book.BookStatus;
import io.spring.booksystem.domain.book.exception.BookNotFoundException;
import io.spring.booksystem.domain.category.CategoryType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("도서 카테고리 수정 유스케이스 테스트")
class BookCategoryUpdateUseCaseTest {


    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookCategoryUpdateUseCase bookCategoryUpdateUseCase;


    @Test
    public void 도서_카테고리_수정_유스케이스_성공() {

        // given
        Book book = Book.of(1L, "이름", "저자", BookStatus.NORMAL, new BookCategories(Arrays.asList(CategoryType.HUMANITIES)));

        when(bookRepository.findById(any()))
            .thenReturn(Optional.of(book));

        // when
        bookCategoryUpdateUseCase.updateBookCategory(new BookCategoryUpdateCommand(book.getId(), Arrays.asList(CategoryType.FICTION)));

        // then
        verify(bookRepository, times(1)).addBookCategories(any(), any(BookCategories.class));
        verify(bookRepository, times(1)).removeBookCategories(any(), any(BookCategories.class));
    }

    @Test
    public void 도서_카테고리_수정_유스케이스_실행시_존재하지않는_도서일경우_예외() {

        // given & when
        when(bookRepository.findById(any()))
            .thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> bookCategoryUpdateUseCase.updateBookCategory(new BookCategoryUpdateCommand(2L, Arrays.asList(CategoryType.FICTION))))
            .isInstanceOf(BookNotFoundException.class);
    }

}
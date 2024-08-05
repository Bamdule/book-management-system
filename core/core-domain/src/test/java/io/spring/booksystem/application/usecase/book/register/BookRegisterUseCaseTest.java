package io.spring.booksystem.application.usecase.book.register;

import io.spring.booksystem.application.usecase.book.BookInfo;
import io.spring.booksystem.domain.book.Book;
import io.spring.booksystem.domain.book.BookCategories;
import io.spring.booksystem.domain.book.BookRepository;
import io.spring.booksystem.domain.book.BookStatus;
import io.spring.booksystem.domain.category.CategoryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("도서 등록 유스케이스 테스트")
class BookRegisterUseCaseTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookRegisterUseCase bookRegisterUseCase;
    ;

    @Test
    public void 도서_등록_유스케이스_성공() {

        // given
        String title = "좋은책";
        String author = "좋은저자";
        List<CategoryType> categoryTypes = Arrays.asList(CategoryType.FICTION);
        BookRegisterCommand command = new BookRegisterCommand(title, author, categoryTypes);

        when(bookRepository.register(any()))
            .thenReturn(Book.of(1L, title, author, BookStatus.NORMAL, new BookCategories(categoryTypes)));

        // when
        BookInfo bookInfo = bookRegisterUseCase.register(command);

        //then
        Assertions.assertAll(
            () -> assertThat(bookInfo.id()).isEqualTo(1L),
            () -> assertThat(bookInfo.title()).isEqualTo(title),
            () -> assertThat(bookInfo.author()).isEqualTo(author),
            () -> assertThat(bookInfo.categoryTypes()).isEqualTo(categoryTypes),
            () -> assertThat(bookInfo.status()).isEqualTo(BookStatus.NORMAL),
            () -> assertThat(bookInfo.isRentalAvailability()).isTrue()
        );
    }

}
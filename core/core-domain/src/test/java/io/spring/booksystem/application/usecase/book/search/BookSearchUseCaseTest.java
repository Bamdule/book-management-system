package io.spring.booksystem.application.usecase.book.search;

import io.spring.booksystem.application.usecase.book.BookInfo;
import io.spring.booksystem.domain.book.Book;
import io.spring.booksystem.domain.book.BookCategories;
import io.spring.booksystem.domain.book.BookRepository;
import io.spring.booksystem.domain.book.BookStatus;
import io.spring.booksystem.domain.category.CategoryType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("도서 목록 조회 유스케이스 테스트")
class BookSearchUseCaseTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookSearchUseCase bookSearchUseCase;


    @Test
    public void 도서_목록_조회_유스케이스_성공() {

        // given
        Book book = Book.of(1L, "이름", "저자", BookStatus.NORMAL, new BookCategories(Arrays.asList(CategoryType.HUMANITIES)));
        List<Book> books = Arrays.asList(book);

        when(bookRepository.findAll(any(), any()))
            .thenReturn(new PageImpl<>(books));

        // when
        Page<BookInfo> searchInfos = bookSearchUseCase.search(new BookSearchQuery(null, null, BookStatus.NORMAL, Arrays.asList(CategoryType.FICTION)), Pageable.ofSize(10));
        
        // then
        BookInfo bookInfo = searchInfos.getContent().get(0);
        Assertions.assertThat(searchInfos.getContent().size()).isEqualTo(books.size());
        Assertions.assertThat(bookInfo.id()).isEqualTo(book.getId());
        Assertions.assertThat(bookInfo.author()).isEqualTo(book.getAuthor());
        Assertions.assertThat(bookInfo.categoryTypes()).isEqualTo(book.getBookCategories().categoryTypes());
        Assertions.assertThat(bookInfo.isRentalAvailability()).isEqualTo(book.isRentalAvailability());
    }
}
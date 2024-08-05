package io.spring.booksystem.domain.book;

import io.spring.booksystem.domain.book.exception.BookAuthorRequiredException;
import io.spring.booksystem.domain.book.exception.BookCategoriesRequiredException;
import io.spring.booksystem.domain.book.exception.BookTitleRequiredException;
import io.spring.booksystem.domain.category.CategoryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("도서 도메인 테스트")
class BookTest {

    @Test
    public void 도서_도메인_생성_성공() {

        // given
        BookCategories bookCategories = new BookCategories(Arrays.asList(CategoryType.ECONOMIC_MANAGEMENT, CategoryType.IT));
        String title = "좋은책";
        String author = "좋은 저자";

        //when
        Book book = Book.create(title, author, bookCategories);

        //then
        Assertions.assertAll(
            () -> assertThat(book.getId()).isNull(),
            () -> assertThat(book.getTitle()).isEqualTo(title),
            () -> assertThat(book.getAuthor()).isEqualTo(author),
            () -> assertThat(book.getBookCategories()).isEqualTo(bookCategories),
            () -> assertThat(book.getStatus()).isEqualTo(BookStatus.NORMAL),
            () -> assertThat(book.isRentalAvailability()).isTrue()
        );
    }

    @Test
    public void 도서_제목_필수값_테스트() {

        // given
        BookCategories bookCategories = new BookCategories(Arrays.asList(CategoryType.ECONOMIC_MANAGEMENT, CategoryType.IT));
        String title = null;
        String author = "좋은 저자";

        //when & then
        assertThatThrownBy(() -> Book.create(title, author, bookCategories))
            .isInstanceOf(BookTitleRequiredException.class);
    }

    @Test
    public void 도서_저자_필수값_테스트() {

        // given
        BookCategories bookCategories = new BookCategories(Arrays.asList(CategoryType.ECONOMIC_MANAGEMENT, CategoryType.IT));
        String title = "좋은책";
        String author = null;

        //when & then
        assertThatThrownBy(() -> Book.create(title, author, bookCategories))
            .isInstanceOf(BookAuthorRequiredException.class);
    }

    @Test
    public void 도서_카테고리_필수값_테스트() {

        // given
        BookCategories bookCategories = null;
        String title = "좋은책";
        String author = "좋은 저자";

        //when & then
        assertThatThrownBy(() -> Book.create(title, author, bookCategories))
            .isInstanceOf(BookCategoriesRequiredException.class);
    }

    @Test
    public void 도서_대여_가능_테스트() {

        // given
        BookCategories bookCategories = new BookCategories(Arrays.asList(CategoryType.ECONOMIC_MANAGEMENT, CategoryType.IT));
        String title = "좋은책";
        String author = "좋은 저자";

        //when
        Book book = Book.create(title, author, bookCategories);


        //then
        assertThat(book.isRentalAvailability()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideBookStatusParameters") // needs to match an existing method.
    public void 도서_대여_불가능_테스트(BookStatus status) {

        // given
        BookCategories bookCategories = new BookCategories(Arrays.asList(CategoryType.ECONOMIC_MANAGEMENT, CategoryType.IT));
        String title = "좋은책";
        String author = "좋은 저자";

        //when
        Book book = Book.of(1L, title, author, status, bookCategories);

        //then
        assertThat(book.isRentalAvailability()).isFalse();
    }

    private static Stream<Arguments> provideBookStatusParameters() { // argument source method
        return Stream.of(
            Arguments.of(BookStatus.LOST),
            Arguments.of(BookStatus.DAMAGED),
            Arguments.of(BookStatus.RENTING)
        );
    }
}
package io.spring.booksystem.domain.book;

import io.spring.booksystem.domain.book.exception.BookCategoriesRequiredException;
import io.spring.booksystem.domain.category.CategoryType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("도서 카테고리 리스트 도메인 테스트")
class BookCategoriesTest {

    @Test
    public void 도서_카테고리_리스트_도메인_생성_성공() {
        // given
        BookCategories bookCategories = new BookCategories(Arrays.asList(CategoryType.ECONOMIC_MANAGEMENT, CategoryType.IT, CategoryType.FICTION));


        // when & then
        assertAll(
            () -> Assertions.assertThat(bookCategories.isEmpty()).isFalse(),
            () -> Assertions.assertThat(bookCategories.size()).isEqualTo(3)
        );
    }

    @Test
    public void 도서_카테고리_리스트_도메인_생성_실패() {
        // given & when & then
        assertThatThrownBy(() -> new BookCategories(null)).isInstanceOf(BookCategoriesRequiredException.class);
    }

    @Test
    public void 도서_카테고리_수정_성공_테스트() {

        // given
        BookCategories originalBookCategories = new BookCategories(Arrays.asList(CategoryType.ECONOMIC_MANAGEMENT, CategoryType.IT, CategoryType.FICTION));
        List<CategoryType> updatedCategoryTypes = Arrays.asList(CategoryType.FICTION, CategoryType.OCCULT, CategoryType.HUMANITIES);

        // when
        BookCategories addBookCategories = originalBookCategories.createAddBookCategories(updatedCategoryTypes);
        BookCategories removeBookCategories = originalBookCategories.createRemoveBookCategories(updatedCategoryTypes);

        //  then
        assertAll(
            () -> Assertions.assertThat(addBookCategories).isEqualTo(new BookCategories(Arrays.asList(CategoryType.OCCULT, CategoryType.HUMANITIES))),
            () -> Assertions.assertThat(removeBookCategories).isEqualTo(new BookCategories(Arrays.asList(CategoryType.ECONOMIC_MANAGEMENT, CategoryType.IT)))
        );
    }
}
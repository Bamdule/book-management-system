package io.spring.booksystem.presentation.book;

import io.spring.booksystem.domain.book.BookRepository;
import io.spring.booksystem.domain.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookControllerTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Test
    void test() {

//        Category category = categoryRepository.register(Category.register(CategoryType.IT));
//
//        Book register = Book.register("", "", Arrays.asList(CategoryType.IT));
//        Book saved = bookRepository.register(register);


//        List<Book> all = bookRepository.findAll();
    }

}
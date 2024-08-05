package io.spring.booksystem.domain.book.exception;

import io.spring.booksystem.domain.common.exception.DomainException;
import io.spring.booksystem.domain.common.exception.ExceptionCode;

public class BookCategoriesRequiredException extends DomainException {
    public BookCategoriesRequiredException() {
        super(ExceptionCode.BOOK_CATEGORIES_REQUIRED);
    }
}

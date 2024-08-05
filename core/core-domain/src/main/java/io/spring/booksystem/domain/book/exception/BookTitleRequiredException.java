package io.spring.booksystem.domain.book.exception;

import io.spring.booksystem.domain.common.exception.DomainException;
import io.spring.booksystem.domain.common.exception.ExceptionCode;

public class BookTitleRequiredException extends DomainException {
    public BookTitleRequiredException() {
        super(ExceptionCode.BOOK_TITLE_REQUIRED);
    }
}

package io.spring.booksystem.domain.book.exception;

import io.spring.booksystem.domain.common.exception.DomainException;
import io.spring.booksystem.domain.common.exception.ExceptionCode;

public class BookAuthorRequiredException extends DomainException {
    public BookAuthorRequiredException() {
        super(ExceptionCode.BOOK_AUTHOR_REQUIRED);
    }
}

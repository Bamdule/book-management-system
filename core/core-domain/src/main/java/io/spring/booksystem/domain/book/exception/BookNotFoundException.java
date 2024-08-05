package io.spring.booksystem.domain.book.exception;

import io.spring.booksystem.domain.common.exception.DomainException;
import io.spring.booksystem.domain.common.exception.ExceptionCode;

public class BookNotFoundException extends DomainException {
    public BookNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}

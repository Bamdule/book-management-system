package io.spring.booksystem.domain.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    BOOK_NOT_FOUND("존재하지 않는 도서입니다.", HttpStatus.NOT_FOUND),
    BOOK_TITLE_REQUIRED("도서 제목은 필수 값 입니다.", HttpStatus.BAD_REQUEST),
    BOOK_AUTHOR_REQUIRED("도서 저자는 필수 값 입니다.", HttpStatus.BAD_REQUEST),
    BOOK_CATEGORIES_REQUIRED("도서 카테고리는 필수 값입니다.", HttpStatus.BAD_REQUEST),
    ;

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}

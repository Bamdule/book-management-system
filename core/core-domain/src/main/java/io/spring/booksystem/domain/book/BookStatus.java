package io.spring.booksystem.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookStatus {
    NORMAL("정상"), RENTING("대여중"), DAMAGED("훼손됨"), LOST("분실됨");
    private final String description;
}

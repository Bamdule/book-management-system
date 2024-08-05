package io.spring.booksystem.storage.jpa.entity.book.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.spring.booksystem.application.usecase.book.search.BookSearchQuery;
import io.spring.booksystem.storage.jpa.entity.book.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static io.spring.booksystem.storage.jpa.entity.book.QBookCategoryEntity.bookCategoryEntity;
import static io.spring.booksystem.storage.jpa.entity.book.QBookEntity.bookEntity;
@RequiredArgsConstructor
public class BookQueryDslRepositoryImpl implements BookQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BookEntity> findAllByQuery(BookSearchQuery query, Pageable pageable) {
        BooleanBuilder conditionBuilder = createWhereBuilder(query);

        List<BookEntity> contents = queryFactory.select(bookEntity)
            .from(bookEntity)
            .leftJoin(bookEntity.bookCategories, bookCategoryEntity)
            .where(conditionBuilder)
            .orderBy(bookEntity.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        ;

        Long count = Optional.ofNullable(
            queryFactory
                .select(bookEntity.countDistinct())
                .from(bookEntity)
                .leftJoin(bookEntity.bookCategories, bookCategoryEntity)
                .where(conditionBuilder)
                .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(contents, pageable, count);
    }

    private BooleanBuilder createWhereBuilder(BookSearchQuery query) {
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (query.title() != null && query.title() != "") {
            conditionBuilder.and(bookEntity.title.like("%" + query.title() + "%"));
        }

        if (query.author() != null && query.author() != "") {
            conditionBuilder.and(bookEntity.author.eq(query.author()));
        }

        if (query.status() != null) {
            conditionBuilder.and(bookEntity.status.eq(query.status()));
        }

        if (query.bookCategoryTypes() != null && !query.bookCategoryTypes().isEmpty()) {
            conditionBuilder.and(bookCategoryEntity.categoryType.in(query.bookCategoryTypes()));
        }
        return conditionBuilder;
    }
}

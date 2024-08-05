package io.spring.booksystem.storage.jpa.entity.category.repository;

import io.spring.booksystem.domain.category.CategoryRepository;
import io.spring.booksystem.domain.category.Category;
import io.spring.booksystem.storage.jpa.entity.category.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CategoryCoreRepository implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Category register(Category category) {
        return categoryJpaRepository.save(CategoryEntity.of(category)).toModel();
    }
}

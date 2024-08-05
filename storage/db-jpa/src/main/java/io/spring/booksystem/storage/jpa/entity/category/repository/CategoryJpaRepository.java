package io.spring.booksystem.storage.jpa.entity.category.repository;

import io.spring.booksystem.domain.category.CategoryType;
import io.spring.booksystem.storage.jpa.entity.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByTypeIn(List<CategoryType> categoryTypes);
}

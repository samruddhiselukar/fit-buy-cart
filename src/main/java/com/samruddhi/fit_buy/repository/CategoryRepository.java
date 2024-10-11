package com.samruddhi.fit_buy.repository;

import com.samruddhi.fit_buy.model.Category;
import com.samruddhi.fit_buy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    boolean existsByName(String name);
}

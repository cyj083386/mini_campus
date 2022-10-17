package com.dokuny.mini_campus.admin.repository;

import com.dokuny.mini_campus.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderBySortValueDesc();
}

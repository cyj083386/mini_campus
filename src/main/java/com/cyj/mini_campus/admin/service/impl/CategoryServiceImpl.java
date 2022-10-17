package com.dokuny.mini_campus.admin.service.impl;

import com.dokuny.mini_campus.admin.dto.CategoryDto;
import com.dokuny.mini_campus.admin.entity.Category;
import com.dokuny.mini_campus.admin.exception.CategoryNotExistException;
import com.dokuny.mini_campus.admin.repository.CategoryRepository;
import com.dokuny.mini_campus.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public boolean add(String categoryName) {

        // 카테고리명 중복 체크
        categoryRepository.save(Category.builder()
                .name(categoryName)
                .usingYn(true)
                .sortValue(0)
                .build());

        return true;
    }

    @Transactional
    @Override
    public boolean update(CategoryDto dto) {

        Category category = categoryRepository.findById(dto.getId())
                .orElseThrow(() ->
                        new CategoryNotExistException("일치하는 카테고리가 존재하지 않습니다."));

        category.setName(dto.getCategoryName());
        category.setSortValue(dto.getSortValue());
        category.setUsingYn(dto.isUsingYn());

        return true;
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDto> list() {
        return CategoryDto.of(categoryRepository.findAllByOrderBySortValueDesc());
    }
}

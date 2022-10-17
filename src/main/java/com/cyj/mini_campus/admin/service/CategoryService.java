package com.dokuny.mini_campus.admin.service;

import com.dokuny.mini_campus.admin.dto.CategoryDto;


import java.util.List;

public interface CategoryService {


    boolean add(String categoryName);

    boolean update(CategoryDto dto);

    boolean delete(long id);

    List<CategoryDto> list();
}

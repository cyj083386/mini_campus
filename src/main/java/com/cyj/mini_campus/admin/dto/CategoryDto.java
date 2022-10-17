package com.dokuny.mini_campus.admin.dto;

import com.dokuny.mini_campus.admin.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryDto {
    private Long id;
    String categoryName;
    int sortValue;
    boolean usingYn;


    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getName())
                .sortValue(category.getSortValue())
                .usingYn(category.isUsingYn())
                .build();
    }

    public static List<CategoryDto> of(List<Category> categories) {
        return categories.stream().map(CategoryDto::of).collect(Collectors.toList());
    }
}

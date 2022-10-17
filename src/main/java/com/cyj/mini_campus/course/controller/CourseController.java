package com.dokuny.mini_campus.course.controller;

import com.dokuny.mini_campus.admin.dto.CategoryDto;
import com.dokuny.mini_campus.admin.service.CategoryService;
import com.dokuny.mini_campus.commons.dto.SearchInput;
import com.dokuny.mini_campus.course.dto.CourseDto;
import com.dokuny.mini_campus.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CourseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/course")
    public String course(Model model, SearchInput input, Pageable pageable) {

        List<CategoryDto> categories = categoryService.list();
        Page<CourseDto> list = courseService.frontList(input, pageable);
        model.addAttribute("list",list);
        model.addAttribute("categories",categories);
        return "course/list";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(Model model, @PathVariable("id") Long id) {

        CourseDto courseDto = courseService.find(id);

        model.addAttribute("course",courseDto);
        return "course/detail";
    }


}

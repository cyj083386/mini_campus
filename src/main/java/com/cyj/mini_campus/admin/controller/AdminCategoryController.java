package com.dokuny.mini_campus.admin.controller;

import com.dokuny.mini_campus.admin.dto.CategoryDeleteInput;
import com.dokuny.mini_campus.admin.dto.CategoryDto;
import com.dokuny.mini_campus.admin.dto.CategoryAddInput;
import com.dokuny.mini_campus.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {

    private final CategoryService categoryService;


    @GetMapping("/admin/category/list.do")
    public String list(Model model) {

        List<CategoryDto> list = categoryService.list();
        model.addAttribute("list", list);

        return "admin/category/list";
    }

    @PostMapping("/admin/category/add.do")
    public String add(CategoryAddInput input) {

        categoryService.add(input.getCategoryName());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/delete.do")
    public String delete(CategoryDeleteInput input) {

        categoryService.delete(input.getId());
        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/update.do")
    public String update(CategoryDto categoryDto) {

        categoryService.update(categoryDto);

        return "redirect:/admin/category/list.do";
    }
}

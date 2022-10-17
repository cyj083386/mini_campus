package com.dokuny.mini_campus.course.controller;

import com.dokuny.mini_campus.commons.dto.Pagination;
import com.dokuny.mini_campus.commons.dto.SearchInput;
import com.dokuny.mini_campus.course.dto.TakeCourseDto;
import com.dokuny.mini_campus.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController {

    private final TakeCourseService takeCourseService;

    @GetMapping({"/admin/takecourse/list.do"})
    public String list(Model model, SearchInput input,
                       @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<TakeCourseDto> list = takeCourseService.list(input, pageable);

        model.addAttribute("page", new Pagination(list));
        model.addAttribute("list", list.getContent());

        return "admin/takecourse/list";
    }

    @PostMapping({"/admin/takecourse/status.do"})
    public String updateStatus(Long id, String status) {

        takeCourseService.updateStatus(id, status);

        return "redirect:/admin/takecourse/list.do";
    }
}

package com.dokuny.mini_campus.admin.controller;

import com.dokuny.mini_campus.admin.dto.BannerAddInput;
import com.dokuny.mini_campus.admin.dto.BannerDto;
import com.dokuny.mini_campus.admin.service.BannerService;
import com.dokuny.mini_campus.commons.dto.Pagination;
import com.dokuny.mini_campus.commons.dto.SearchInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class AdminBannerController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, SearchInput input, @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<BannerDto> list = bannerService.list(input, pageable);

        model.addAttribute("list",list.getContent());
        model.addAttribute("page", new Pagination(list));

        return "admin/banner/list";
    }

    @GetMapping("/admin/banner/add.do")
    public String addForm() {
        return "admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String add(BannerAddInput input) {
        bannerService.add(input);
        return "redirect:/admin/banner/list.do";
    }

    @GetMapping("/admin/banner/edit.do")
    public String editForm(Model model,@RequestParam("id") Long id) {

        BannerDto detail = bannerService.detail(id);
        model.addAttribute("banner", detail);
        return "admin/banner/edit";
    }

    @PostMapping("/admin/banner/edit.do")
    public String edit(BannerDto input, MultipartFile img) {
        bannerService.update(input,img);
        return "redirect:/admin/banner/list.do";
    }




    @PostMapping("/admin/banner/delete.do")
    public String delete(@RequestParam("idList") String idList) {
        bannerService.remove(idList);
        return "redirect:/admin/banner/list.do";
    }


}

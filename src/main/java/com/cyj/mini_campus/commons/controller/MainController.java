package com.dokuny.mini_campus.commons.controller;

import com.dokuny.mini_campus.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final BannerService bannerService;


    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("banners", bannerService.front());
        return "index";
    }
}

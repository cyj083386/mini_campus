package com.dokuny.mini_campus.admin.controller;

import com.dokuny.mini_campus.admin.service.BannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("admin")
@Controller
public class AdminMainController {


    @GetMapping("/main.do")
    public String main() {


        return "admin/main";
    }


}

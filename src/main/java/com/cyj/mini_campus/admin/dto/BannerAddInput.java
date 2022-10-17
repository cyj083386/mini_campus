package com.dokuny.mini_campus.admin.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BannerAddInput {
    private String name;
    private String alterText;
    private String linkUrl;
    private String target;
    private boolean openYn;
    private Long sortOrder;
    private MultipartFile img;
}

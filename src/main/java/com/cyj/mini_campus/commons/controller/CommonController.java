package com.dokuny.mini_campus.commons.controller;

import com.dokuny.mini_campus.commons.service.FileUploadService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;

@RequiredArgsConstructor
@Controller
public class CommonController {

    private final FileUploadService fileUploadService;


    @RequestMapping("/errors")
    public String errorPage() {
        return "commons/error";
    }


    @ResponseBody
    @RequestMapping("/imageupload")
    public ResponseEntity imageUpload(MultipartHttpServletRequest multiFile) throws IOException {

        MultipartFile file = multiFile.getFile("upload");

        JsonObject upload = fileUploadService.uploadEditorImg(file);

        return ResponseEntity.ok().body(upload.toString());
    }
}

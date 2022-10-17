package com.dokuny.mini_campus.commons.service;

import com.google.gson.JsonObject;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    JsonObject uploadEditorImg(MultipartFile file);

    String uploadImgFile(MultipartFile file);

}

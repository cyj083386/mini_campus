package com.dokuny.mini_campus.commons.service;

import com.dokuny.mini_campus.commons.entity.FileUpload;
import com.dokuny.mini_campus.commons.repository.FileUploadRepository;
import com.dokuny.mini_campus.commons.utils.FileTimePath;
import com.dokuny.mini_campus.course.exception.FileInvalidException;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${spring.servlet.multipart.location}")
    private String rootPath;

    @Value("${fileUrlRoot}")
    private String urlRoot;

    private final FileUploadRepository fileUploadRepository;

    @Transactional
    @Override
    public JsonObject uploadEditorImg(MultipartFile file) {
        String urlPath = uploadImgFile(file);

        JsonObject json = new JsonObject();

        json.addProperty("uploaded",1);
        json.addProperty("fileName",file.getOriginalFilename());
        json.addProperty("url",urlPath);
        return json;
    }

    @Transactional
    @Override
    public String uploadImgFile(MultipartFile file) {
        if (!checkImageFile(file)) {
            log.error("정상적인 이미지 파일이 아닙니다.");
            throw new FileInvalidException("정상적인 이미지 파일이 아닙니다.");
        }

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String saveFileName = getSaveFileName(extension);
        Path localPath = getLocalPath(saveFileName);

        saveInLocal(file,localPath);

        fileUploadRepository.save(FileUpload.builder()
                .extension(extension)
                .size(file.getSize())
                .originalFilename(file.getOriginalFilename())
                .saveFilename(saveFileName)
                .urlPath(getUrlPath(saveFileName))
                .localPath(localPath.toString())
                .build());

        return getUrlPath(saveFileName);
    }


    private boolean checkImageFile(MultipartFile file) {
        if (file != null) { // 파일 null 체크
            if (file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) { // 사이즈 체크 및 파일 이름 체크
                if (file.getContentType().toLowerCase().startsWith("image/")) {
                   return true;
                }
            }
        }
        return false;
    }

    private void saveInLocal(MultipartFile file, Path localPath)  {
        try {
            file.transferTo(localPath);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private String getUrlPath(String filename) {
        return urlRoot + FileTimePath.getDayPath("/") + filename;
    }

    private Path getLocalPath(String filename) {
        makeDir(FileTimePath.getAllPath(File.separator));
        return Paths.get(rootPath + FileTimePath.getDayPath(File.separator) + filename);
    }

    private String getSaveFileName(String extension) {
        return UUID.randomUUID().toString().replace("-", "") + "." + extension;
    }

    private void makeDir(String path) {
        File dir = new File(rootPath + path);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
    }
    private void makeDir(String[] paths) {
        for (String path : paths) {
            makeDir(path);
        }
    }


}

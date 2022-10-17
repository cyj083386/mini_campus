package com.dokuny.mini_campus.commons.repository;

import com.dokuny.mini_campus.commons.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}

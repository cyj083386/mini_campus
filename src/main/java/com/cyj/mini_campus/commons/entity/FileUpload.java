package com.dokuny.mini_campus.commons.entity;

import com.dokuny.mini_campus.course.entity.Course;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FileUpload extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String extension;

    private Long size;

    private String originalFilename;

    private String saveFilename;

    private String urlPath;

    private String localPath;


}

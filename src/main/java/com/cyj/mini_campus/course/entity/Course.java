package com.dokuny.mini_campus.course.entity;

import com.dokuny.mini_campus.admin.entity.Category;
import com.dokuny.mini_campus.commons.entity.BaseTimeEntity;
import com.dokuny.mini_campus.commons.entity.FileUpload;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Course extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Category category;

    private String imagePath;

    private String keyword;

    private String subject;

    @Column(length = 1000)
    private String summary;

    @Lob
    private String contents;

    private long price;

    private long salePrice;

    private LocalDateTime saleEndAt;

}

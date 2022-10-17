package com.dokuny.mini_campus.admin.entity;

import com.dokuny.mini_campus.admin.type.BannerTargetStatus;
import com.dokuny.mini_campus.commons.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Banner extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean openYn;

    private String alterText;

    private String linkUrl;

    @Enumerated(EnumType.STRING)
    private BannerTargetStatus target;

    private Long sortOrder;

    private String imgUrl;
}

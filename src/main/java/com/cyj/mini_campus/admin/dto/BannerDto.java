package com.dokuny.mini_campus.admin.dto;

import com.dokuny.mini_campus.admin.entity.Banner;
import com.dokuny.mini_campus.admin.type.BannerTargetStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BannerDto {

    private Long id;

    private String name;

    private boolean openYn;

    private String alterText;

    private String linkUrl;

    private BannerTargetStatus target;

    private Long sortOrder;

    private String imgUrl;

    private LocalDateTime registeredAt;

    public BannerDto(String alterText, String linkUrl, String imgUrl,BannerTargetStatus target) {
        this.alterText = alterText;
        this.linkUrl = linkUrl;
        this.imgUrl = imgUrl;
        this.target = target;
    }

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .openYn(banner.isOpenYn())
                .alterText(banner.getAlterText())
                .linkUrl(banner.getLinkUrl())
                .target(banner.getTarget())
                .sortOrder(banner.getSortOrder())
                .imgUrl(banner.getImgUrl())
                .registeredAt(banner.getCreatedAt())
                .build();
    }

    public void changeEntity(Banner banner) {
        banner.setAlterText(alterText);
        banner.setImgUrl(imgUrl);
        banner.setName(name);
        banner.setOpenYn(openYn);
        banner.setTarget(target);
        banner.setSortOrder(sortOrder);
        banner.setLinkUrl(linkUrl);
    }

}

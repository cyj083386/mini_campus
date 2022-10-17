package com.dokuny.mini_campus.admin.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BannerTargetStatus {
    NEW("_blank"),
    CURRENT("_self");

    public String value;
}

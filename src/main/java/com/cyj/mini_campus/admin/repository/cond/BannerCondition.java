package com.dokuny.mini_campus.admin.repository.cond;


import com.dokuny.mini_campus.commons.dto.SearchInput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BannerCondition {

    private String name;
    private Boolean openYn;

    public static BannerCondition of(SearchInput input) {
        BannerConditionBuilder builder = BannerCondition.builder();

        if (input.getSearchType() == null) {
        } else if (input.getSearchType().equals("name")) {
            builder.name(input.getSearchValue());
        } else if (input.getSearchType().equals("openYn")) {
            if (input.getSearchValue().equals("true")) {
                builder.openYn(true);
            } else {
                builder.openYn(false);
            }
        }

        return builder.build();
    }
}

package com.dokuny.mini_campus.admin.repository.cond;

import com.dokuny.mini_campus.commons.dto.SearchInput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberSearchCondition {
    private String email;
    private String name;
    private String phone;

    public static MemberSearchCondition of(SearchInput input) {
        MemberSearchConditionBuilder builder = builder();

        if (input.getSearchType() == null) {
        } else if (input.getSearchType().equals("email")) {
            builder.email(input.getSearchValue());
        } else if (input.getSearchType().equals("name")) {
            builder.name(input.getSearchValue());
        } else if (input.getSearchType().equals("phone")) {
            builder.phone(input.getSearchValue());
        }

        return builder.build();
    }
}

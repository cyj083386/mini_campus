package com.dokuny.mini_campus.course.repository.cond;

import com.dokuny.mini_campus.commons.dto.SearchInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseSearchCondition {

    private String subject;

    private String categoryName;


    public static CourseSearchCondition of(SearchInput input) {
        CourseSearchConditionBuilder builder = CourseSearchCondition.builder();

        if (input.getSearchType() == null) {

        } else if (input.getSearchType().equals("subject")) {
            builder.subject(input.getSearchValue());
        } else if (input.getSearchType().equals("categoryName")) {
            builder.categoryName(input.getSearchValue());
        }

        return builder.build();
    }
}

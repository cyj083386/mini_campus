package com.dokuny.mini_campus.course.repository.cond;

import com.dokuny.mini_campus.commons.dto.SearchInput;
import com.dokuny.mini_campus.course.type.TakeCourseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.util.StringUtils.hasText;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TakeCourseSearchCondition {

    private TakeCourseStatus status;
    private String memberId;

    public static TakeCourseSearchCondition of(SearchInput input) {
        TakeCourseSearchConditionBuilder builder = TakeCourseSearchCondition.builder();

        if (input.getSearchType() == null) {
        } else {
            builder.status(TakeCourseStatus.valueOf(input.getSearchType()));
        };

        return builder.build();
    }

    public static TakeCourseSearchCondition of(String memberId) {
        TakeCourseSearchConditionBuilder builder = TakeCourseSearchCondition.builder();

        if (hasText(memberId)) {
            builder.memberId(memberId);
        }
        return builder.build();
    }

}

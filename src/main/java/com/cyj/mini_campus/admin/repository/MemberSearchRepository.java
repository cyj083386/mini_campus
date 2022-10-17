package com.dokuny.mini_campus.admin.repository;

import com.dokuny.mini_campus.admin.dto.MemberListDto;
import com.dokuny.mini_campus.admin.repository.cond.MemberSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberSearchRepository {
    Page<MemberListDto> searchMembers(MemberSearchCondition condition, Pageable pageable);
}


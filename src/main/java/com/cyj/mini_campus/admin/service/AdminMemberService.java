package com.dokuny.mini_campus.admin.service;

import com.dokuny.mini_campus.admin.dto.MemberDetailDto;
import com.dokuny.mini_campus.admin.dto.MemberListDto;
import com.dokuny.mini_campus.commons.dto.SearchInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AdminMemberService {

    Page<MemberListDto> searchMembers(SearchInput input, Pageable pageable);

    MemberDetailDto getMemberDetail(String id,Pageable pageable);

    void editMemberDetail(MemberDetailDto dto);
}

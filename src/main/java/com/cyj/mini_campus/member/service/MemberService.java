package com.dokuny.mini_campus.member.service;

import com.dokuny.mini_campus.admin.dto.MemberDetailDto;
import com.dokuny.mini_campus.admin.dto.MemberInfoDto;
import com.dokuny.mini_campus.member.dto.MemberRegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MemberService extends UserDetailsService {

    void register(MemberRegisterRequest request);

    void activateAuthEmail(String authKey);

    void findPassword(String id, String name);

    void resetPassword(String pwKey, String password);

    MemberInfoDto info(String memberId);

    boolean updateInfo(MemberInfoDto infoDto);


    boolean withdrawal(String memberId);
}

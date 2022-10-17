package com.dokuny.mini_campus.commons.handler;


import com.dokuny.mini_campus.commons.utils.RequestUtils;
import com.dokuny.mini_campus.member.entity.LoginHistory;
import com.dokuny.mini_campus.member.repository.LoginHistoryRepository;
import com.dokuny.mini_campus.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class MemberAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("===== save Longin Log =====  {}",authentication.getName());
        loginHistoryRepository.save(LoginHistory.builder()
                .member(memberRepository.getReferenceById(authentication.getName()))
                .userAgent(RequestUtils.getUserAgent(request))
                .ip(RequestUtils.getIP(request))
                .build());



        super.onAuthenticationSuccess(request,response,authentication);
    }
}

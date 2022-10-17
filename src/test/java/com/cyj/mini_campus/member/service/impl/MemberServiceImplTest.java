package com.dokuny.mini_campus.member.service.impl;

import com.dokuny.mini_campus.commons.component.MailComponent;
import com.dokuny.mini_campus.member.dto.MemberRegisterRequest;
import com.dokuny.mini_campus.member.entity.Member;
import com.dokuny.mini_campus.member.entity.MemberAuth;
import com.dokuny.mini_campus.member.repository.MemberAuthRepository;
import com.dokuny.mini_campus.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.*;


@ExtendWith({MockitoExtension.class})
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MailComponent mailComponent;

    @Mock
    private MemberAuthRepository memberAuthRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("회원가입 성공")
    void register_Success() {
        //given
        Member member = Member.builder()
                .id("test@test.com")
                .build();
        MemberAuth auth = MemberAuth.builder()
                .member(member)
                .emailAuthKey("test")
                .build();

        given(memberRepository.existsById(anyString()))
                .willReturn(false);
        given(memberRepository.save(any()))
                .willReturn(member);
        given(memberAuthRepository.save(any()))
                .willReturn(auth);

        given(mailComponent.sendMail(anyString(), anyString(), anyString()))
                .willReturn(true);

        ArgumentCaptor<MemberAuth> captor = ArgumentCaptor.forClass(MemberAuth.class);

        //when
        memberService.register(MemberRegisterRequest.builder()
                .email("test@test.com")
                .name("test")
                .password("test")
                .phone("test")
                .build());

        //then
        verify(memberAuthRepository).save(captor.capture());

        assertFalse(captor.getValue().isAuthYn());
        assertEquals("test@test.com",captor.getValue().getMember().getId());
    }
}
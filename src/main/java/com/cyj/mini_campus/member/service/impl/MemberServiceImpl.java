package com.dokuny.mini_campus.member.service.impl;

import com.dokuny.mini_campus.admin.dto.MemberInfoDto;
import com.dokuny.mini_campus.commons.component.MailComponent;

import com.dokuny.mini_campus.member.dto.MemberRegisterRequest;
import com.dokuny.mini_campus.member.entity.MemberAuth;
import com.dokuny.mini_campus.member.entity.Member;
import com.dokuny.mini_campus.member.exception.MemberAuthException;
import com.dokuny.mini_campus.member.exception.MemberNotExistException;
import com.dokuny.mini_campus.member.exception.MemberRegisterException;
import com.dokuny.mini_campus.member.repository.MemberAuthRepository;
import com.dokuny.mini_campus.member.repository.MemberRepository;
import com.dokuny.mini_campus.member.service.MemberService;
import com.dokuny.mini_campus.member.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberAuthRepository memberAuthRepository;
    private final MailComponent mailComponent;

    @Override
    @Transactional
    public void register(MemberRegisterRequest request) {
        if (memberRepository.existsById(request.getEmail())) {
            throw new MemberRegisterException("이미 가입된 이메일 입니다.");
        }

        Member member = request.toEntity();
        MemberAuth memberAuth = createDefaultAuth(member);

        member.setMemberAuth(memberAuth);

        memberRepository.save(member);

        sendAuthMail(member.getId(), memberAuth.getEmailAuthKey());
    }

    private MemberAuth createDefaultAuth(Member member) {
        return MemberAuth.builder()
                .emailAuthKey(UUID.randomUUID().toString())
                .authYn(false)
                .build();
    }


    /**
     * 후에 제목 및 내용은 데이터베이스에 저장하여 불러다가 사용하도록 변경할 예정입니다.
     */
    private boolean sendAuthMail(String email, String authKey) {
        String subject = "Mini Campus 인증 메일입니다.";
        String text = "<p>아래의 링크를 클릭하셔서 이메일 인증을 진행해주세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/register/email-auth?authKey="
                + authKey
                + "'> 이메일 인증 </a></div>";

        return mailComponent.sendMail(email, subject, text);
    }

    @Transactional
    @Override
    public void activateAuthEmail(String authKey) {
        MemberAuth auth = memberAuthRepository.findByEmailAuthKeyAndAuthYnIsFalse(authKey)
                .orElseThrow(() -> new MemberAuthException("인증 키가 유효하지 않습니다."));

        auth.activateAuth();
        auth.getMember().changeStatus(MemberStatus.VALID);
    }

    @Transactional
    @Override
    public void findPassword(String email, String name) {

        Member member = memberRepository
                .findById(email)
                .orElseThrow(() -> new MemberAuthException("이메일이 존재하지 않습니다."));

        if (!member.getName().equals(name)) {
            throw new MemberAuthException("이메일과 이름이 일치하지 않습니다.");
        }

        sendPasswordMail(member.getId(), member.getMemberAuth().issuePasswordAuthKey());
    }

    private boolean sendPasswordMail(String email, String passwordKey) {
        String subject = "Mini Campus 비밀번호 찾기 메일입니다.";
        String text = "<p>아래의 링크를 클릭하셔서 비밀번호를 변경해주세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/reset/password?passwordKey="
                + passwordKey
                + "'> 이메일 인증 </a></div>";

        return mailComponent.sendMail(email, subject, text);
    }

    @Transactional
    public void resetPassword(String passwordKey, String password) {
        MemberAuth auth = memberAuthRepository.findByPwAuthKey(passwordKey)
                .orElseThrow(() -> new MemberAuthException("존재하지 않는 비밀번호 키입니다."));

        auth.closeFindPasswordKey();

        auth.getMember().changePassword(password);
    }

    @Transactional(readOnly = true)
    @Override
    public MemberInfoDto info(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotExistException("존재하지 않는 회원입니다."));

        return MemberInfoDto.of(member);
    }

    @Transactional
    @Override
    public boolean updateInfo(MemberInfoDto infoDto) {
        Member member = memberRepository.findById(infoDto.getId())
                .orElseThrow(() -> new MemberNotExistException("존재하지 않는 회원입니다."));

        member.updateInfo(infoDto.getPassword(),
                infoDto.getPhone(),
                infoDto.getName(),
                infoDto.getZipcode(),
                infoDto.getAddr(),
                infoDto.getAddrDetail());

        return false;
    }

    @Transactional
    @Override
    public boolean withdrawal(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotExistException("존재하지 않는 회원입니다."));

        member.changeStatus(MemberStatus.WITHDRAWAL);
        return true;
    }


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {

        Member member = memberRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        checkMemberStatus(member);

        return createUserDetails(member);
    }

    private UserDetails createUserDetails(Member member) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(member.getRole().name()));

        return new User(member.getId(), member.getPassword(), grantedAuthorities);
    }

    private void checkMemberStatus(Member member) {
        if (member.getStatus().equals(MemberStatus.WAIT)) {
            throw new MemberAuthException("인증이 필요한 회원입니다.");
        } else if (member.getStatus().equals(MemberStatus.BANNED)) {
            throw new MemberAuthException("정지된 회원 입니다.");
        } else if (member.getStatus().equals(MemberStatus.WITHDRAWAL)) {
            throw new MemberAuthException("탈퇴한 회원 입니다.");
        }
    }
}

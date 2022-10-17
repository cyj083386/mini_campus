package com.dokuny.mini_campus.member.entity;

import com.dokuny.mini_campus.commons.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class MemberAuth extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(optional = false,fetch = FetchType.LAZY,mappedBy = "memberAuth")
    private Member member;
    private boolean authYn;

    private String emailAuthKey;

    private String pwAuthKey;

    private LocalDateTime authenticatedAt;


    public void activateAuth() {
        this.authYn = true;
        this.authenticatedAt = LocalDateTime.now();
        this.emailAuthKey = null;
    }

    public String issuePasswordAuthKey() {
        this.pwAuthKey = UUID.randomUUID().toString();

        return this.pwAuthKey;
    }

    public void closeFindPasswordKey() {
        this.pwAuthKey = null;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

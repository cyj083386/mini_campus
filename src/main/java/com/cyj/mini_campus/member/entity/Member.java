package com.dokuny.mini_campus.member.entity;

import com.dokuny.mini_campus.admin.dto.MemberInfoDto;
import com.dokuny.mini_campus.admin.dto.MemberListDto;
import com.dokuny.mini_campus.commons.entity.BaseTimeEntity;
import com.dokuny.mini_campus.commons.role.Role;
import com.dokuny.mini_campus.member.type.MemberStatus;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
public class Member extends BaseTimeEntity implements Persistable<String> {

    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL,orphanRemoval = true)
    private MemberAuth memberAuth;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MemberStatus status;

    private String zipcode;
    private String addr;
    private String addrDetail;

    public void changePassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }


    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }

    public void setMemberAuth(MemberAuth memberAuth) {
        this.memberAuth = memberAuth;
        memberAuth.setMember(this);
    }

    public void updateInfo(String password,String phone,String name,String zipcode,String addr,String addrDetail) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.phone = phone;
        this.name = name;
        this.zipcode = zipcode;
        this.addr =addr;
        this.addrDetail =addrDetail;
    }

    public void changeStatus(MemberStatus status) {
        this.status = status;
    }
}

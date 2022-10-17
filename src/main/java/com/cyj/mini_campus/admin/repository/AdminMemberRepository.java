package com.dokuny.mini_campus.admin.repository;

import com.dokuny.mini_campus.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminMemberRepository extends JpaRepository<Member,String>,MemberSearchRepository {
}

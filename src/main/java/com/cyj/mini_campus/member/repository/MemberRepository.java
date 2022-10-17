package com.dokuny.mini_campus.member.repository;

import com.dokuny.mini_campus.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
}

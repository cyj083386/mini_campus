package com.dokuny.mini_campus.member.repository;

import com.dokuny.mini_campus.member.entity.MemberAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberAuthRepository extends JpaRepository<MemberAuth, Long> {


    Optional<MemberAuth> findByEmailAuthKeyAndAuthYnIsFalse(String key);

    Optional<MemberAuth> findByPwAuthKey(String pwKey);





}

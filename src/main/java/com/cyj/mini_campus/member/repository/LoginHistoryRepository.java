package com.dokuny.mini_campus.member.repository;


import com.dokuny.mini_campus.member.dto.LoginHistoryDto;
import com.dokuny.mini_campus.member.entity.LoginHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    @Query(value = "select new com.dokuny.mini_campus.member.dto.LoginHistoryDto(l.userAgent,l.ip,l.createdAt) from LoginHistory l where l.member.id = :memberId order by l.createdAt desc",
            countQuery = "select count(l) from LoginHistory l where l.member.id = :memberId")
    Page<LoginHistoryDto> findAllByMemberId(@Param("memberId")String memberId, Pageable pageable);
}

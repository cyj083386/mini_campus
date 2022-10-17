package com.dokuny.mini_campus;


import com.dokuny.mini_campus.member.entity.Member;
import com.dokuny.mini_campus.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QueryDslTest {

    @PersistenceContext
    EntityManager em;


    @Test
    void contextLoad() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QMember member = QMember.member;

        Member result = query.selectFrom(member)
                .where(member.id.eq("nestour@naver.com"))
                .fetchOne();

        assertEquals( "nestour@naver.com",result.getId());
        assertEquals("test",result.getName());


    }

}

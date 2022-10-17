package com.dokuny.mini_campus.admin.repository.impl;

import com.dokuny.mini_campus.admin.dto.MemberListDto;
import com.dokuny.mini_campus.admin.repository.cond.MemberSearchCondition;
import com.dokuny.mini_campus.admin.repository.MemberSearchRepository;
import com.dokuny.mini_campus.member.entity.QLoginHistory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dokuny.mini_campus.member.entity.QLoginHistory.*;
import static com.dokuny.mini_campus.member.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class MemberSearchRepositoryImpl implements MemberSearchRepository {

    private final JPAQueryFactory queryFactory;

    public MemberSearchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<MemberListDto> searchMembers(MemberSearchCondition condition, Pageable pageable) {

        List<MemberListDto> content = queryFactory.
                select(Projections.bean(MemberListDto.class,
                        member.id,
                        member.name,
                        member.phone,
                        member.createdAt.as("registeredAt"),
                        member.role,
                        loginHistory.createdAt.as("lastLoginAt")))
                .from(member)
                .leftJoin(loginHistory)
                .on(member.id.eq(loginHistory.member.id),loginHistory.createdAt.eq(
                                JPAExpressions
                                        .select(loginHistory.createdAt.max())
                                        .from(loginHistory)
                                        .where(member.id.eq(loginHistory.member.id))))
                .where(emailContains(condition.getEmail()),
                        nameContains(condition.getName()),
                        phoneContains(condition.getPhone()))
                .orderBy(member.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

//        List<MemberListDto> content = queryFactory.
//                select(Projections.bean(MemberListDto.class,
//                        member.id,
//                        member.name,
//                        member.phone,
//                        member.createdAt.as("registeredAt"),
//                        member.role))
//                .from(member)
//                .where(emailContains(condition.getEmail()),
//                        nameContains(condition.getName()),
//                        phoneContains(condition.getPhone()))
//                .orderBy(member.id.asc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member)
                .where(emailContains(condition.getEmail()),
                        nameContains(condition.getName()),
                        phoneContains(condition.getPhone()));


        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }


    private BooleanExpression emailContains(String email) {
        return hasText(email) ? member.id.contains(email) : null;
    }

    private BooleanExpression nameContains(String name) {
        return hasText(name) ? member.name.contains(name) : null;
    }

    private BooleanExpression phoneContains(String phone) {
        return hasText(phone) ? member.phone.contains(phone) : null;
    }


}

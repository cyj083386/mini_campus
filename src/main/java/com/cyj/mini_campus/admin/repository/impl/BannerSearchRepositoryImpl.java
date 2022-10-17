package com.dokuny.mini_campus.admin.repository.impl;

import com.dokuny.mini_campus.admin.dto.BannerDto;
import com.dokuny.mini_campus.admin.entity.QBanner;
import com.dokuny.mini_campus.admin.repository.BannerSearchRepository;
import com.dokuny.mini_campus.admin.repository.cond.BannerCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dokuny.mini_campus.admin.entity.QBanner.*;
import static com.dokuny.mini_campus.member.entity.QLoginHistory.loginHistory;
import static com.dokuny.mini_campus.member.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class BannerSearchRepositoryImpl implements BannerSearchRepository {

    private final JPAQueryFactory queryFactory;

    public BannerSearchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<BannerDto> findAllByDto(BannerCondition condition, Pageable pageable) {

        List<BannerDto> content = queryFactory.
                select(Projections.bean(BannerDto.class,
                        banner.id,
                        banner.name,
                        banner.openYn,
                        banner.alterText,
                        banner.linkUrl,
                        banner.target,
                        banner.sortOrder,
                        banner.imgUrl,
                        banner.createdAt.as("registeredAt")))
                .from(banner)
                .where(nameContains(condition.getName()),
                        openYnEq(condition.getOpenYn()))
                .orderBy(banner.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(banner.count())
                .from(banner)
                .where(nameContains(condition.getName()),
                        openYnEq(condition.getOpenYn()));


        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }


    private BooleanExpression nameContains(String name) {
        return hasText(name) ? banner.name.contains(name) : null;
    }

    private BooleanExpression openYnEq(Boolean openYn) {
        return openYn != null ? banner.openYn.eq(openYn) : null;
    }




}

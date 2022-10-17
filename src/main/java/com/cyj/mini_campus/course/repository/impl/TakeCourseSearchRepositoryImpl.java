package com.dokuny.mini_campus.course.repository.impl;

import com.dokuny.mini_campus.course.dto.TakeCourseDto;
import com.dokuny.mini_campus.course.repository.TakeCourseSearchRepository;
import com.dokuny.mini_campus.course.repository.cond.CourseSearchCondition;
import com.dokuny.mini_campus.course.repository.cond.TakeCourseSearchCondition;
import com.dokuny.mini_campus.course.type.TakeCourseStatus;
import com.dokuny.mini_campus.member.entity.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dokuny.mini_campus.course.entity.QCourse.course;
import static com.dokuny.mini_campus.course.entity.QTakeCourse.*;
import static com.dokuny.mini_campus.member.entity.QMember.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class TakeCourseSearchRepositoryImpl implements TakeCourseSearchRepository {

    private final JPAQueryFactory queryFactory;

    public TakeCourseSearchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<TakeCourseDto> search(TakeCourseSearchCondition cond, Pageable pageable) {

        List<TakeCourseDto> content = queryFactory.
                select(Projections.bean(TakeCourseDto.class,
                        takeCourse.id,
                        course.subject,
                        member.id.as("memberId"),
                        member.phone.as("memberPhone"),
                        takeCourse.status,
                        takeCourse.createdAt.as("registeredAt")))
                .from(takeCourse)
                .join(takeCourse.member, member)
                .join(takeCourse.course, course)
                .where(statusEq(cond.getStatus()),memberIdEq(cond.getMemberId()))
                .orderBy(takeCourse.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(takeCourse.count())
                .from(takeCourse)
                .where(statusEq(cond.getStatus()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }


    private BooleanExpression statusEq(TakeCourseStatus status) {
        return status != null ? takeCourse.status.eq(status) : null;
    }

    private BooleanExpression memberIdEq(String memberId) {
        return hasText(memberId) ? member.id.eq(memberId) : null;
    }


}

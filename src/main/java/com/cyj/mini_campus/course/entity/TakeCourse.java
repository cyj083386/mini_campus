package com.dokuny.mini_campus.course.entity;

import com.dokuny.mini_campus.commons.entity.BaseTimeEntity;
import com.dokuny.mini_campus.course.type.TakeCourseStatus;
import com.dokuny.mini_campus.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class TakeCourse extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    private long payPrice;

    @Enumerated(EnumType.STRING)
    private TakeCourseStatus status;


}

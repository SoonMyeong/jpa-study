package com.study.chap10;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Member findByName(String name) {
        QMember qMember = QMember.member;
        return jpaQueryFactory
                .selectFrom(qMember)
                .where(qMember.name.eq(name))
                .fetchOne();
    }

}

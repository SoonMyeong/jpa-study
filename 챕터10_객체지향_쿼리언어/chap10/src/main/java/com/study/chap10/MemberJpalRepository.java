package com.study.chap10;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberJpalRepository extends JpaRepository<Member,Long> {
    @Query("select m from Member m where m.name = ?1")
    Member findMemberByName(String name);

}

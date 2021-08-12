package com.study.chap10;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository  extends JpaRepository<Member,Long> {

}

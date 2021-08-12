package com.study.chap10;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TestServiceTest {

    @Autowired
    private MemberJpalRepository memberJpalRepository;
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("jpql 확인")
    @Test
    public void jpqlExample(){
        Member member = memberJpalRepository.findMemberByName("순명");
        assertEquals(member.getName(),"순명");
    }

    @DisplayName("queryDSL 확인")
    @Test
    public void queryDSL(){
        Member member = memberRepository.findByName("순명");
        assertEquals(member.getName(),"순명");
    }

}
package com.hoya.chapter05;

import com.hoya.chapter05.model.Member;
import com.hoya.chapter05.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Slf4j
class Chapter05ApplicationTests {
    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void initial() {
        this.emf = Persistence.createEntityManagerFactory("jpas");
        this.em = emf.createEntityManager();
        EntityTransaction tx  = em.getTransaction();
        tx.begin();

        /* PERSIST TEAM 1 */
        Team testTeam = new Team();
        testTeam.setId(1L);
        testTeam.setName("두산");
        em.persist(testTeam);

        /* PERSIST MEMBER 1 */
        Member m1 = new Member();
        m1.setId(1L);
        m1.setTeam(testTeam);
        m1.setUserName("testUSer");
        em.persist(m1);

        /* PERSIST MEMBER 2 */
        Member m2 = new Member();
        m2.setId(2L);
        m2.setTeam(testTeam);
        m2.setUserName("bareUser");
        em.persist(m2);
        tx.commit();
        testTeam.getMembers().add(m1);
        testTeam.getMembers().add(m2);
//        em.clear();
    }



    @Test
    @DisplayName("1. ONEWAY RELATIONSHIP")
    void oneWay() {


//        em.clear();
// 영속성에 남아 있을경우 다른 어플리케이션과 동기화...?

// A. application user name 은 "테스터";
// B. application user name update 은 "김길동";
        Member resultMember = em.find(Member.class, 1L);
        log.info("==================== {} ", resultMember.getUserName());
    }

    @Test
    @DisplayName("2.BIDIRECTIONAL RELATIONSHIP")
    void bidirectional() {

        Team team = em.find(Team.class, 1L);
        List<Member> members = team.getMembers();

        log.info("members {}", members.size());
        for(Member m : members){
            log.info("==================== {}", m.getUserName() );
        }
    }

    @Test
    @DisplayName("3.EXCLUDE JOIN COLUMN")
    void excludeJoinColumn() {

        String jpqlQuery = "select m from Member m join m.team t where t.name =:teamName";

        List<Member> resultMembers = em.createQuery(jpqlQuery, Member.class)
                .setParameter("teamName", "두산" +
                        "").getResultList();


    }



    @Test
    @DisplayName("2.BIDIRECTIONAL RELATIONSHIP")
    void jpqlSelector() {

        String jpqlQuery = "select m from Member m join m.team t where t.name =:teamName";

        List<Member> resultMembers = em.createQuery(jpqlQuery, Member.class)
                .setParameter("teamName", "두산").getResultList();


    }





}

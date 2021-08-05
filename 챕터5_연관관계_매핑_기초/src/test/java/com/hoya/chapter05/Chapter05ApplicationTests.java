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
    private Team t1;

    @BeforeEach
    void initial() {
        this.emf = Persistence.createEntityManagerFactory("jpas");
        this.em = emf.createEntityManager();
        EntityTransaction tx  = em.getTransaction();
        tx.begin();

        /* PERSIST TEAM 1 */
        this.t1 = new Team();
        t1.setId(1L);
        t1.setName("두산");
        em.persist(t1);

        /* PERSIST MEMBER 1 */
        Member m1 = new Member();
        m1.setId(1L);
        m1.setTeam(t1);
        m1.setUserName("member1");
        em.persist(m1);

        /* PERSIST MEMBER 2 */
        Member m2 = new Member();
        m2.setId(2L);
        m2.setTeam(t1);
        m2.setUserName("member2");
        em.persist(m2);

        tx.commit();
//        testTeam.getMembers().add(m1);
//        testTeam.getMembers().add(m2);
//        em.clear();
    }


    @Test
    @DisplayName("5.6 SAVE MEMBER & TEAM")
    void save() {
        EntityTransaction tx  = em.getTransaction();
        tx.begin();

        /* PERSIST TEAM 2 */
        Team t2 = new Team();
        t2.setId(2L);
        t2.setName("기아");

        // TODO: 2021-07-02 TESTING
        // !!! WHEN WITH OUT PERSIST >>> MAKE ERROR
        em.persist(t2);

        /* PERSIST MEMBER 3 */
        Member m3 = new Member();
        m3.setId(3L);
        m3.setTeam(t2);
        m3.setUserName("member3");
        em.persist(m3);

        /* PERSIST MEMBER 4 */
        Member m4 = new Member();
        m4.setId(4L);
        m4.setTeam(t2);
        m4.setUserName("member4");
        em.persist(m4);

        tx.commit();
    }

    @Test
    @DisplayName("2.BIDIRECTIONAL RELATIONSHIP")
    void bidirectional() {

        Team team = em.find(Team.class, 1L);
        List<Member> members = team.getMembers();

        log.info("TEST@ >>  members : {}", members.size());
        for(Member m : members){
            log.info("TEST@ >>  member : {}", m.getUserName() );
        }
    }

    @Test
    @DisplayName("5.7 JPQL JOIN RETRIEVE")
    void jpqlSelector() {

        String jpqlQuery = "select m from member m join m.team t where t.name =:teamName";

        List<Member> resultMembers = em.createQuery(jpqlQuery, Member.class)
                .setParameter("teamName", "두산").getResultList();

        for(Member m : resultMembers){
            log.info("TEST@ >>  m.getUserName() {}", m.getUserName());
        }
    }

    @Test
    @DisplayName("5.8 UPDATE")
    void updateTeam() {
        EntityTransaction tx  = em.getTransaction();
        tx.begin();
        Team t2 = new Team(2L, "한화");
        em.persist(t2);
        Member m1 = em.find(Member.class, 1L);
        m1.setTeam(t2);
        tx.commit();
    }


    @Test
    @DisplayName("5.8 DELETE")
    void deleteRelationWithRelations() {
        EntityTransaction tx  = em.getTransaction();
        tx.begin();

        Member m1 = em.find(Member.class, 1L);
        m1.setTeam(null);
        Member m2 = em.find(Member.class, 2L);
        // TODO: 2021-07-02 TESTING
        // !!! WHEN WITH OUT TEAM NULL >>> MAKE ERROR
        // Cannot delete or update a parent row: a foreign key constraint fails
        m2.setTeam(null);

        em.remove(t1);

        tx.commit();
    }

    @Test
    @DisplayName("5.13 bidSave")
    public void bidiSave(){
        EntityTransaction tx  = em.getTransaction();
        tx.begin();

        Team t10 = new Team(10L, "삼성");
        em.persist(t10);

        Member m14 = new Member(14L, "member14");
        m14.setTeam(t10);
        em.persist(m14);

        Member m15 = new Member(15L, "member15");
        m15.setTeam(t10);
        em.persist(m15);

        tx.commit();

        Member m14r = em.find(Member.class, 14L);
        Member m15r = em.find(Member.class, 14L);

        log.info("TEST@ >> m14r. {} {}",m14r.getUserName(), m14r.getTeam());
        log.info("TEST@ >> m15r. {} {}}",m15.getUserName(),  m15r.getTeam());
    }




    @Test
    @DisplayName("5.14 SaveNonOwner")
    public void bidiSaveNonOwner(){
        EntityTransaction tx  = em.getTransaction();
        tx.begin();

        Member m14 = new Member(14L, "member14");
        em.persist(m14);

        Member m15 = new Member(15L, "member15");
        em.persist(m15);

        Team t10 = new Team(10L, "삼성");
        t10.getMembers().add(m14);
        t10.getMembers().add(m15);

        em.persist(t10);

        tx.commit();


        Member m14r = em.find(Member.class, 14L);
        Member m15r = em.find(Member.class, 14L);

        log.info("TEST@ >> m14r : {}", m14r.getTeam());
        log.info("TEST@ >> m15r : {}", m15r.getTeam());
    }

    @Test
    @DisplayName("5.16 bidiSavePureRelations")
    public void bidiSavePureRelations(){

        Team t10 = new Team(10L, "삼성");
        Member m14 = new Member(14L, "member14");
        Member m15 = new Member(15L, "member15");


        m14.setTeam(t10);
        t10.getMembers().add(m14);
        m15.setTeam(t10);
        t10.getMembers().add(m15);
        log.info("TEST@ >>  : {} members: {}", t10, t10.getMembers());
    }


    @Test
    @DisplayName("5.17 bidiSaveJPA_ORM")
    public void bidiSaveJPA_ORM(){
        EntityTransaction tx  = em.getTransaction();
        tx.begin();

        Team t10 = new Team(10L, "삼성");
        em.persist(t10);

        Member m14 = new Member(14L, "member14");
        m14.setTeam(t10);
        t10.getMembers().add(m14);
        em.persist(m14);


        Member m15 = new Member(15L, "member15");
        m15.setTeam(t10);
        t10.getMembers().add(m15);
        em.persist(m15);

        tx.commit();
        Team t10r = em.find(Team.class, 10L);
        log.info("TEST@ >>  : {} members: {}", t10r, t10r.getMembers());
    }



}

import entity.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("챕터3 테스트 자료")
public class JpaTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private Member member;

    @BeforeEach
    void setUp() {
        this.emf = Persistence.createEntityManagerFactory("jpabook");
        this.em = emf.createEntityManager();
        //비영속 상태
        this.member = new Member();
        this.member.setId("member1");
        this.member.setUsername("soon");
        this.member.setAge(2);
    }

    @AfterEach
    void clear() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createQuery("delete from Member").executeUpdate();
        tx.commit();
    }


    @Test
    @DisplayName("1차캐시 조회")
    void entity_cash_test() {
        em.persist(member);
        Member a = em.find(Member.class,"member1");

        assertEquals(member.getId(),a.getId());
        assertEquals(member.getUsername(),a.getUsername());
        assertEquals(member.getAge(),a.getAge());
    }

    @Test
    @DisplayName("캐시에 entity 없을 경우 DB 조회")
    void entity_notfound_cash() {
        EntityTransaction tx  = em.getTransaction();
        tx.begin();
        Member memberA = new Member();
        memberA.setAge(2021);
        memberA.setUsername("jpa");
        memberA.setId("memberA");
        em.persist(memberA);
        tx.commit();
        em.clear();

        System.out.println("===========DB 조회===========");
        Member a = em.find(Member.class,"memberA");
        System.out.println("==========캐시 조회============");
        Member b = em.find(Member.class,"memberA");
        System.out.println("==========메소드 끝============");
    }


    @Test
    @DisplayName("동일성 비교")
    void object_identity_test() {
        em.persist(member);
        Member a = em.find(Member.class,"member1");
        Member b = em.find(Member.class,"member1");

        assertEquals(a,b);
        assertSame(a,b);
    }

    @Test
    @DisplayName("트랜잭션 지원하는 쓰기지연, 트랜잭션 종료 시 한번에 쿼리를 전송 한다.")
    void transaction_write_behind() {
        EntityTransaction tx = em.getTransaction();
        Member a = new Member("member1","test1",2021);
        Member b = new Member("member2","test2",2021);
        tx.begin();
        System.out.println("==========영속성 등록===========");
        em.persist(a);
        em.persist(b);
        System.out.println("===========영속성 등록 완료==========");
        tx.commit();
    }

    @Test
    @DisplayName("변경 감지")
    void dirty_checking() {
        EntityTransaction tx = em.getTransaction();
        System.out.println("=====변경 감지 시작=====");
        tx.begin();
        em.persist(member); //영속 상태
        member.setUsername("변경 감지");
        tx.commit();

        Member test = em.find(Member.class,"member1");
        assertEquals(test.getUsername(),"변경 감지");
        System.out.println("=====변경 감지 종료=====");
    }

    @Test
    @DisplayName("강제 flush")
    void entityManager_flush() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(member);
        em.flush();
        System.out.println("======강제 flush 끝=====");
        member.setUsername("하이버네이트");

        tx.commit();
    }

    @Test
    @DisplayName("JPQL 쿼리 실행 시 flush 자동 호출")
    void exec_jpql_auto_flush() {
        Member b = new Member("member2","테스트",2021);
        Member c = new Member("member3","테스트",2021);
        em.persist(member);
        em.persist(b);
        em.persist(c);

        //JPQL 쿼리 실행 시 auto flush가 작동 안한다면? -> DB 조회 결과는 없어야 한다. why? 플러시 안했으니까
        List<Member> members = em.createQuery("select m from Member m",Member.class).getResultList();
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("챕터3 테스트 자료")
public class JpaTest {
    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        this.emf = Persistence.createEntityManagerFactory("jpabook");
        this.em = emf.createEntityManager();
    }

    @Test
    @DisplayName("동일성 비교")
    void object_identity_test() {
        String id = "member1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("soon");
        member.setAge(2);

        em.persist(member);

        Member a = em.find(Member.class,"member1");
        Member b = em.find(Member.class,"member1");

        assertEquals(a,b);
    }





}

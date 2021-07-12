package mappedsuperclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@DisplayName("7-2 @MappedSuperclass 테스트")
public class Chapter7_2Tests {
    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        this.emf = Persistence.createEntityManagerFactory("chapter7");
        this.em = emf.createEntityManager();
    }

    @DisplayName("Member 와 Seller 생성")
    @Test
    void createMemberAndSeller() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(Member.builder()
                .name("순명")
                .email("soonworld91")
                .build());
        em.persist(Seller.builder()
                .name("순명")
                .shopName("soonShop")
                .build());

        tx.commit();
    }
}

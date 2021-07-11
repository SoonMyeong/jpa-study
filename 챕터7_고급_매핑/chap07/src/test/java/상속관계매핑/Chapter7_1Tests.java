package 상속관계매핑;

import joinstrategy.Album;
import joinstrategy.Book;
import joinstrategy.Item;
import joinstrategy.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("7-1 상속 관계 매핑 테스트")
public class Chapter7_1Tests {
    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        this.emf = Persistence.createEntityManagerFactory("chapter7");
        this.em = emf.createEntityManager();
    }

    void close() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createQuery("delete from Album ").executeUpdate();
        em.createQuery("delete from Movie ").executeUpdate();
        em.createQuery("delete from Book ").executeUpdate();
        em.createQuery("delete from Item ").executeUpdate();
        tx.commit();
    }

    @DisplayName("조인 전략 테스트 (콘솔 확인용)")
    @Test
    void joinStrategyTest() {
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(Album.builder()
                .name("쑨앨범")
                .price(10000)
                .artist("Soon 아티스트")
                .build());

        em.persist(Movie.builder()
                .name("쑨무비")
                .price(30000)
                .director("쑨감독")
                .actor("쑤니")
                .build());

        em.persist(Book.builder()
                .name("쑨북")
                .price(50000)
                .author("쑨명")
                .isbn("ABC")
                .build());
        tx.commit();
        close();
    }

    @DisplayName("단일 테이블전략 테스트 (콘솔 확인), Item 클래스에 가서 전략을 바꾸고, 구분컬럼 명시x 하고 테스트 해보기")
    @Test
    void oneTableStrategyTest() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(Album.builder()
                .name("쑨앨범")
                .price(10000)
                .artist("Soon 아티스트")
                .build());
        tx.commit();
    }



}

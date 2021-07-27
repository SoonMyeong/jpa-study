package proxy;

import proxy.eager.Member;
import proxy.eager.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Kim Donghoon on 2015-11-24.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Member member = em.find(Member.class, 1L);
       //   Member member2 = em.getReference(Member.class, 2L);

        proxy.lazy.Team team = new proxy.lazy.Team();
        team.setName("kimehdgns");
        em.persist(team);

        proxy.lazy.Member lazyMember = new proxy.lazy.Member();
        lazyMember.setTeam(team);
        lazyMember.setUserName("동훈 김");
        em.persist(lazyMember);
        em.flush();
        em.clear();

        //proxy.lazy.Member lazyMember2 = em.find(proxy.lazy.Member.class, 1L);
       // proxy.lazy.Member lazyMember3 = em.getReference(proxy.lazy.Member.class, 4L);

        System.out.println("==========================================================");
        System.out.println("==========================================================");

        //System.out.println(lazyMember2.getTeam().getName());

        //System.out.println(lazyMember3.getTeam().getName());
        tx.commit();
        em.close();
        emf.close();
    }

    public static void printUserAndTeam(EntityManager em, String memberId) {
        Member member = em.find(Member.class, memberId);
        Team team = member.getTeam();
        System.out.println("회원 이름 : " + member.getUserName());
        System.out.println("소속팀 : " + team.getName());
    }

    public static void printUser(EntityManager em, String memberId) {
        Member member = em.find(Member.class, memberId);
        System.out.println("회원 이름 : " + member.getUserName());
    }
}

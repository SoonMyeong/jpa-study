package cascade.remove;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        init(em);

        Parent parent = em.find(Parent.class, 1L);
        em.remove(parent);

        tx.commit();
        em.close();
        emf.close();
    }

    public static void init(EntityManager em) {
        Parent parent = new Parent();

        Child child1 = new Child();
        child1.setParent(parent);
        parent.getChildren().add(child1);

        Child child2 = new Child();
        child2.setParent(parent);
        parent.getChildren().add(child2);

        em.persist(parent);
        em.flush();
        em.clear();
    }
}

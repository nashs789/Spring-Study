package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /* - Insert
            Member mem = new Member();
            mem.setId(2L);
            mem.setName("helloB");
            em.persist(mem);
             */

            /* - Find
            Member mem = em.find(Member.class, 2L);
            System.out.println("mem.getId() = " + mem.getId());
            System.out.println("mem.getName() = " + mem.getName());
             */

            /* - Update
            Member mem = em.find(Member.class, 1L);
            mem.setName("HelloJPA");
             */

            List<Member> res = em.createQuery("select m from Member as m", Member.class)
                                 .setFirstResult(1)
                                 .setMaxResults(2)
                                 .getResultList();

            for(Member mem : res) {
                System.out.println("mem.getName() = " + mem.getName());   
            }

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
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

            /*
            List<Member> res = em.createQuery("select m from Member as m", Member.class)
                                 .setFirstResult(1)
                                 .setMaxResults(2)
                                 .getResultList();

            for(Member mem : res) {
                System.out.println("mem.getName() = " + mem.getName());   
            }
            */

            /* - Persistence Context
            Member mem = new Member();
            mem.setId(101L);
            mem.setName("Hello-Hi");

            System.out.println("=== Before ===");
            em.persist(mem);
            System.out.println("=== After ===");

            Member resMem = em.find(Member.class, 101L);

            System.out.println("resMem.getId() = " + resMem.getId());
            System.out.println("resMem.getName() = " + resMem.getName());
             */

            /* - First Level Cache
            Member mem = new Member();
            mem.setId(101L);
            mem.setName("Hello-Hi");

            Member resMem1 = em.find(Member.class, 101L);
            Member resMem2 = em.find(Member.class, 101L);
            */

            /* - transactional write-behind
            Member mem1 = new Member(150L, "A");
            Member mem2 = new Member(160L, "B");

            em.persist(mem1);
            em.persist(mem2);
             */

            /* - Dirty Checking
            Member mem = em.find(Member.class, 150L);
            mem.setName("ZZZZZ");
             */

            /* - Detach
            Member mem = em.find(Member.class, 150L);
            mem.setName("AAAAA");

            em.detach(mem);
             */

            // em.clear(); 영속성 컨텍스트 모두 detach

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
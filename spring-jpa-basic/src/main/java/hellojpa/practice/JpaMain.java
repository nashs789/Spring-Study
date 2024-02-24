//package hellojpa;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//
//import java.util.List;
//
//public class JpaMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try {
//            /* - Insert
//            hellojpa.Member mem = new hellojpa.Member();
//            mem.setId(2L);
//            mem.setName("helloB");
//            em.persist(mem);
//             */
//
//            /* - Find
//            hellojpa.Member mem = em.find(hellojpa.Member.class, 2L);
//            System.out.println("mem.getId() = " + mem.getId());
//            System.out.println("mem.getName() = " + mem.getName());
//             */
//
//            /* - Update
//            hellojpa.Member mem = em.find(hellojpa.Member.class, 1L);
//            mem.setName("HelloJPA");
//             */
//
//            /*
//            List<hellojpa.Member> res = em.createQuery("select m from hellojpa.Member as m", hellojpa.Member.class)
//                                 .setFirstResult(1)
//                                 .setMaxResults(2)
//                                 .getResultList();
//
//            for(hellojpa.Member mem : res) {
//                System.out.println("mem.getName() = " + mem.getName());
//            }
//            */
//
//            /* - Persistence Context
//            hellojpa.Member mem = new hellojpa.Member();
//            mem.setId(101L);
//            mem.setName("Hello-Hi");
//
//            System.out.println("=== Before ===");
//            em.persist(mem);
//            System.out.println("=== After ===");
//
//            hellojpa.Member resMem = em.find(hellojpa.Member.class, 101L);
//
//            System.out.println("resMem.getId() = " + resMem.getId());
//            System.out.println("resMem.getName() = " + resMem.getName());
//             */
//
//            /* - First Level Cache
//            hellojpa.Member mem = new hellojpa.Member();
//            mem.setId(101L);
//            mem.setName("Hello-Hi");
//
//            hellojpa.Member resMem1 = em.find(hellojpa.Member.class, 101L);
//            hellojpa.Member resMem2 = em.find(hellojpa.Member.class, 101L);
//            */
//
//            /* - transactional write-behind
//            hellojpa.Member mem1 = new hellojpa.Member(150L, "A");
//            hellojpa.Member mem2 = new hellojpa.Member(160L, "B");
//
//            em.persist(mem1);
//            em.persist(mem2);
//             */
//
//            /* - Dirty Checking
//            hellojpa.Member mem = em.find(hellojpa.Member.class, 150L);
//            mem.setName("ZZZZZ");
//             */
//
//            /* - Detach
//            hellojpa.Member mem = em.find(hellojpa.Member.class, 150L);
//            mem.setName("AAAAA");
//
//            em.detach(mem);
//             */
//
//            // em.clear(); 영속성 컨텍스트 모두 detach
//
//            // PK generate strategy
//            hellojpa.Member mem = new hellojpa.Member();
//            mem.setUsername("C");
//
//            em.persist(mem);
//
//            tx.commit();
//        } catch(Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//            emf.close();
//        }
//    }
//}
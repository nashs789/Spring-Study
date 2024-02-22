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
//            Member member = new Member();
//            member.setUsername("UserA");
//
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("TeamA");
//            team.getMembers().add(member);
//
//            em.persist(team);
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
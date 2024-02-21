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
//            // 저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member mem = new Member();
//            mem.setUsername("Member1");
//            mem.setTeam(team);
//            em.persist(mem);
//
//            // 영속성 컨텍스트 초기화
//            em.flush();
//            em.clear();
//
//            Member findMem = em.find(Member.class, mem.getId());
//            // Team findTeam = em.find(Team.class, mem.getTeam().getId());
//            List<Member> members = findMem.getTeam().getMembers();
//
//            for(Member member : members) {
//                System.out.println("member = " + member.getUsername());
//            }
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
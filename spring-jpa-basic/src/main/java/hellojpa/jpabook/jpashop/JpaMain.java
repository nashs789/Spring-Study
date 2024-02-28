package hellojpa.jpabook.jpashop;

import hellojpa.jpabook.jpashop.domain.Book;
import hellojpa.jpabook.jpashop.domain.Item;
import hellojpa.jpabook.jpashop.domain.Member;
import hellojpa.jpabook.jpashop.domain.Order;
import hellojpa.jpabook.jpashop.domain.enums.OrderStatus;
import jakarta.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Order order = new Order();

            order.setStatus(OrderStatus.ORDER);

            em.persist(order);
            em.flush();
            em.clear();

            //Order order1 = em.find(Order.class, order.getId());
            Order order1 = em.getReference(Order.class, order.getId());
            PersistenceUnitUtil utils = emf.getPersistenceUnitUtil();

            System.out.println(utils.isLoaded(order1));
            System.out.println(order1.getStatus());
            System.out.println(utils.isLoaded(order1));

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
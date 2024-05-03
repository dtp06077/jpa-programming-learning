package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //비영속
            Member member = new Member();
            member.setId(6L);
            member.setName("memberA");

            //영속
            System.out.println("==BEFORE==");
            //영속성 컨텍스트에 persist해도 바로 db에 반영되지 않음.
            //AFTER가 출력된 후에 쿼리가 나감.
            em.persist(member);
            System.out.println("==AFTER==");

           List<Member> result = em.createQuery("select m from Member as m", Member.class)
                   .setFirstResult(2)
                   .setMaxResults(4)
                   .getResultList();

            //커밋 시점에 영속성 컨텍스트에 영속된 엔티티들이 db에 반영
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

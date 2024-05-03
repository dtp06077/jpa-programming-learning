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

            //1차 캐시에서 엔티티를 가져오므로 select 쿼리가 나가지 않음.
            Member findMember = em.find(Member.class, 6L);
            Member findMember2 = em.find(Member.class, 6L);

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            //영속 엔티티의 동일성 보장
            System.out.println("result = " + (findMember2==findMember));

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

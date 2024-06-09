package jpql;

import jakarta.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("KimHuiSeong");
            member.setAge(20);
            em.persist(member);

            //TypeQuery : 반환 타입이 명확할 때 사용(ex Member.class)
            TypedQuery<Member> typeQuery = em.createQuery("select m from Member m", Member.class);

            //Query : 반환 타입이 명확하지 않을 때 사용(String username, int age)
            Query query = em.createQuery("select m.username, m.age from Member m");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}

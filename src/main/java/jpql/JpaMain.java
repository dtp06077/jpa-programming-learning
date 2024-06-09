package jpql;

import jakarta.persistence.*;

import java.util.List;

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

            //결과가 하나 이상일 떄, 리스트 반환
            //결과가 없으면 빈 리스트 반환
            List<Member> resultList = typeQuery.getResultList();

            //결과가 정확히 하나일 때, 단일 객체 반환
            //결과가 없거나 둘 이상이면 Exception 발생
            Member singleResult = typeQuery.getSingleResult();

            //Query : 반환 타입이 명확하지 않을 때 사용(String username, int age)
            Query query = em.createQuery("select m.username, m.age from Member m");

            //파라미터 바인딩 - 이름 기준
            Member findMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                            .setParameter("username", "KimHuiSeong")
                            .getSingleResult();

            System.out.println("result = "+findMember.getUsername());

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

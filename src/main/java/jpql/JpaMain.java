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

            //연관관계 엔티티 프로젝션
            //조인 문법이 실행되는지 예측 불가 -> 바람직한 코딩 X
            em.createQuery("select m.team from Member m",Team.class);
            //조인 문법이 보이게 jpql 을 작성하는 것이 바람직
            em.createQuery("select t from Member m join m.team t",Team.class);

            //Query 타입으로 조회
            List resultList1 = em.createQuery("select m.username, m.age from Member m")
                    .getResultList();
            Object o = resultList1.get(0);
            Object[] result1 = (Object[]) o;
            System.out.println("queryResult = "+ result1[0]+" "+ result1[1]);

            //Object[] 타입으로 조회
            List<Object[]> objectList1 = em.createQuery("select m.username, m.age from Member m")
                    .getResultList();
            Object[] result2 = objectList1.get(0);
            System.out.println("objectResult = "+ result2[0]+" "+ result2[1]);

            //new 명령어(dto)로 조회
            List<MemberDTO> dtoList1 = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = dtoList1.get(0);
            System.out.println("dtoResult = "+ memberDTO.getUsername()+" "+ memberDTO.getAge());

            //페이징 API
            //setFirstResult : 조회 시작 위치
            //setMaxResult : 조회할 데이터 수
            em.createQuery("select m from Member m order by m.id desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(5);

            //내부 조인
            em.createQuery("select m from Member m inner join m.team t");

            //외부 조인
            em.createQuery("select m from Member m left outer join m.team t");
            //조인 대상 필터링
            em.createQuery("select m from Member m left join m.team t on t.name = 'A'");

            //세타 조인
            em.createQuery("select m from Member m, Team t where m.username = t.name");
            //조인 대상 필터링
            em.createQuery("select m, t from Member m left join Team t on m.username = t.name");

            //서브쿼라
            //팀A 소속인 회원
            em.createQuery("select m from Member m where exists(select t from m.team t where t.name = '팀A')");
            //전체 상품 각각의 재고보다 주문량이 많은 주문들
            em.createQuery("select o from Order o where o.orderAmount > ALL(select p.stockAmount from Product p)");
            //어떤 팀이든 팀에 소속된 회원
            em.createQuery("select m from Member m where m.team = ANY(select t from Team t)");

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

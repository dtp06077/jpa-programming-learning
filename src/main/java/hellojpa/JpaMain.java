package hellojpa;

import jakarta.persistence.*;


public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member memberA = new Member();
            memberA.setUsername("memberA");
            memberA.setAge(30);
            memberA.setRoleType(RoleType.ADMIN);

            Team teamA = new Team();
            teamA.setName("TeamA");
            em.persist(teamA);

            memberA.setTeam(teamA);
            em.persist(memberA);

            //참조를 사용해서 연관관계 조회
            Member findMember = em.find(Member.class, memberA.getId());
            Team findTeam = findMember.getTeam();

            //역방향 조회
            //Team 엔티티에서 자신을 참조하는 Member 엔티티의 수를 찾음
            int memberCount = findTeam.getMembers().size();

            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            //연관관계 수정
            findMember.setTeam(teamB);


            //비영속
            //Member memberB = new Member();
            //memberB.setId(7L);
            //memberB.setUsername("memberB");
            //em.persist(memberB);

            //엔티티 매니저는 엔티티 등록 시 INSERT 쿼리를 바로 db에 보내지 않음.
            //트랜잭션을 지원하는 쓰기 지연
            //System.out.println("=========");
            //Member memberC = new Member();
            //memberC.setId(8L);
            //memberC.setUsername("memberB");
            //em.persist(memberC);

            //영속성 컨텍스트의 변경내용을 db에 반영하는 flush
            //트랜잭션 커밋시 자동 호출
            //직접 호출
            //em.flush();
            //jpql 쿼리 실행시에도 자동 호출됨
            //List<Member> memberList = em.createQuery("select m from Member as m", Member.class)
            //        .getResultList();

            //영속 상태의 엔티티가 영속성 컨텍스트에서 분리 -> 준영속
            //특정 엔테티만 준영속 상태로 전환
            //em.detach(memberC);
            //영속성 컨텍스트 초기화
            //em.clear();
            //영속성 컨텍스트 종료
            //em.close();

            //영속
            //System.out.println("==BEFORE==");
            //영속성 컨텍스트에 persist해도 바로 db에 반영되지 않음.
            //AFTER가 출력된 후에 쿼리가 나감.
            //em.persist(member);
            //System.out.println("==AFTER==");

            //1차 캐시에서 엔티티를 가져오므로 select 쿼리가 나가지 않음.
            //Member findMember = em.find(Member.class, 6L);
            //Member findMember2 = em.find(Member.class, 6L);

            //System.out.println("findMember.id = " + findMember.getId());
            //System.out.println("findMember.name = " + findMember.getUsername());

            //영속 엔티티 동일성 보장
            //System.out.println("result = " + (findMember2==findMember));

            //Member findA = em.find(Member.class, 6L);
            //Member findC = em.find(Member.class, 8L);
            //영속 컨텍스트의 변경 감지
            //findA.setUsername("DirtyChecking");
            //findC.setUsername("memberC");

            //List<Member> result = em.createQuery("select m from Member as m", Member.class)
            //        .setFirstResult(2)
            //        .setMaxResults(4)
            //        .getResultList();


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

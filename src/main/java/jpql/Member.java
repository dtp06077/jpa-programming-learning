package jpql;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
//Named 쿼리 - 정적 쿼리, 미리 정의해서 이름을 부여해두고 사용하는 JPQL
//          - 애플리케이션 로딩 시점에 쿼리를 검증, 초기화 후 재사용
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username")
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    //연관관계 편의 메서드
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}

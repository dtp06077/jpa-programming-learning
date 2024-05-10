package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    //양방향 연관관계는 외래키를 갖는 테이블이 연관관계의 주인이 됨(여기서는 Member)
    //연관관계에서 주인이 아닌 엔티티는 mappedBy를 통해 주인 지정
    //주인(Member)만이 외래 키(TEAM_ID)를 관리하고 주인이 아닌(Team)쪽은 읽기만 가능
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}

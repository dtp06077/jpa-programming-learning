package jpql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Team {

    @Id @GeneratedValue
    private Long id;

    private String name;

    //LAZY 로딩 기법의 한계점(n+1 문제)을 해결할 수 있는 어노테이션
    //컬렉션을 패치 조인할 때 size 만큼의 엔티티를 한번에 가져옴.
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    //연관관계 편의 메서드
    public void addMember(Member member) {
        member.setTeam(this);
        this.members.add(member);
    }
}

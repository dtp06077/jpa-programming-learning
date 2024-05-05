package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Member") //JPA에서 사용할 엔티티 이름을 지정
@Getter @Setter
@Table(name = "MEMBER") //매핑할 테이블 이름
public class Member {

    @Id
    private Long id;
    @Column(unique = true, length = 10)
    private String name;
}

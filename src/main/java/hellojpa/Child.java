package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Child {

    @Id @GeneratedValue
    private Long id;

    private String name;

    //다대일 관계
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;
}

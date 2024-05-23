package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

    //다대다 양방향 매핑
    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();
}

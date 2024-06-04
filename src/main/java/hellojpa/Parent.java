package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Parent {

    @Id @GeneratedValue
    private Long id;

    private String name;

    //일대다 관계
    //cascade : 영속성 전이 -> 특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께
    //                       영속 상태로 만듦.
    //orphanRemoval : 고아 객체 제거 -> 부모 엔티티와 연관관계가 끊어진 자식 엔티티를
    //                                자동으로 삭제.
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    //연관관계 편의 메서드
    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }
}

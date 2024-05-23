package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//연결 테이블용 엔티티
@Entity
@Getter @Setter
public class MemberProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

}

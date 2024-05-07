package hellojpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ITEM_ID")
    private Long itemId;

    private int orderPrice;

    private int count;
}

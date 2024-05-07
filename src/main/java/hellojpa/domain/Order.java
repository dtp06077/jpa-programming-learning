package hellojpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Getter @Setter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long memberId;

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}

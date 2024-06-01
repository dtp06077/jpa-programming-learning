package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @Inheritance(strategy=InheritanceType.XXX) -> 상속관계 매핑 메서드
 * 슈퍼타입 서브타입 논리 모델을 실제 물리 모델로 구현
 * 각각 테이블로 변환 -> 조인 전략(JOINED)
 * 통합 테이블로 변환 -> 단일 테이블 전략(SINGLE_TABLE)
 * 서브타입 테이블로 변환 -> 구현 클래스마다 테이블 전략(TABLE_PER_CLASS) ->잘 사용 X
 */
@Entity @Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn //DTYPE 값 설정, 싱글 테이블 전략에서는 자동 생성
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;
}

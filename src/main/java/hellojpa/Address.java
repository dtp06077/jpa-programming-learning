package hellojpa;

import jakarta.persistence.Embeddable;
import lombok.*;

// 임베디드 타입
// 임베디드 타입은 객체 타입이므로 실제 인스턴스인 값을 공유하면 문제 발생.
// 따라서 인스턴스 값들을 final 로 정의하거나, set 메서드를 제거하여 수정할 수 없게하여
// 생성 시점 이후 절대 값을 변경할 수 없는 불변 객체로 설계.
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class Address {
    private final String city;
    private final String street;
    private final String zipcode;
}

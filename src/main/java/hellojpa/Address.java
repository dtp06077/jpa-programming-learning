package hellojpa;

import jakarta.persistence.Embeddable;
import lombok.*;

//임베디드 타입
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter @Setter
public class Address {
    private final String city;
    private final String street;
    private final String zipcode;
}

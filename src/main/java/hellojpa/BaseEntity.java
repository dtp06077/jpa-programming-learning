package hellojpa;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

// 공통 매핑 정보(email, phoneNumber)가 필요할 때 사용
// 공통 속성을 묶어서 처리하므로 굉장히 유용
@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {
    private String email;
    private int phoneNumber;
}

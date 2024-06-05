package hellojpa;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;

//임베디드 타입
@Embeddable
@Getter
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

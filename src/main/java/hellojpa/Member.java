package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Member") //JPA에서 사용할 엔티티 이름을 지정
@Getter @Setter
@Table(name = "MEMBER") //매핑할 테이블 이름
public class Member {

    @Id
    private Long id;

    //name - 필드와 매핑할 테이블의 컬럼 이름
    //updatable - 변경 가능 여부
    //unique - 컬럼에 유니크 제약 조건 걸 때 사용
    //length - String 타입 문자 길이 제약 조건
    @Column(name = "name", updatable = false, unique = true, length = 10)
    private String username;

    private Integer age;

    //EnumType - 기본값은 ORDINAL. ORDINAL을 사용하면 enum의 순서를 db에 저장
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //TemporalType - 날짜 타입을 매핑할 때 사용
    //LocalDate를 사용할 떄는 생략 가능
    private LocalDate createDate;

    private LocalDateTime lastModifiedDate;

    //Lob - 데이터베이스 BLOB, CLOB 타입과 매핑
    @Lob
    private String description;

    //Transient - 필드 매핑X, 데이터베이스 저장X, 조회X
    //메모리상에서만 임시로 어떤 값을 보관하고 싶을 때 사용
    @Transient
    private Integer empty;
}

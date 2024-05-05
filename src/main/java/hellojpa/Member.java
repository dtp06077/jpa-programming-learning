package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Member") //JPA에서 사용할 엔티티 이름을 지정
@Getter @Setter
@Table(name = "MEMBER") //매핑할 테이블 이름
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)//initialValue - 처음 시작하는 시퀀스 수
                                            //allocationSize = 시퀀스 한 번 호출에 증가하는 수
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {

    /**
     * 기본 키 매핑 방법
     * GeneratedValue - 자동 생성
     * IDENTITY - 기본 키 생성을 데이터베이스에 위임
     *          - 데이터베이스에 INSERT 쿼리 실행 후 id값을 알 수 있음 -> 반영 전까진 null
     *          - 영속성 컨텍스트는 이러한 이유로 em.persist() 시점에 즉시 INSERT 쿼리 실행
     * SEQUENCE - 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트
     * TABLE - 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 모방
     */

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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



package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class Member extends BaseEntity {

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
    @Column(name = "MEMBER_ID")
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

    //객체의 참조와 테이블의 외래 키를 매핑
    //지연 로딩 LAZY를 사용해서 프록시로 조회
    //즉시 로딩은 JPQL에서 N+1 문제를 일으키므로 가급적 지연 로딩만 사용
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    //일대일  양방향 매핑
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    //다대다 양방향 매핑
    //연결 테이블에 데이터를 넣을 방법이 없음
    //실무에서 거의 사용 X
    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();

    //다대다 매핑의 한계를 보완
    //연결테이블용 엔티티를 추가하여 연관관계 매핑
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    /**
     * 임베디드 타입
     *  - 높은 재사용성과 응집도를 가짐.
     *  - 임베디드 타입을 포함한 모든 값 타입은, 값 타입을 소유한 엔티티에 생명주기 의존
     *  - 기본 생성자가 필수.
     */
    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    //연관관계 편의 메소드
    //연관관계의 주인에 값을 입력하는 것은 필수
    private void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}



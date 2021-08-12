package joinstrategy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
//상속 매핑의 경우 부모 클래스에 @Inheritance를 사용 해야 한다, 전략은 조인전략 사용
//@Inheritance(strategy = InheritanceType.JOINED)

//단일 테이블 전략 시 사용
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

//부모 클래스에 구분 컬럼을 지정 한다.
@DiscriminatorColumn(name = "DTYPE")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
}

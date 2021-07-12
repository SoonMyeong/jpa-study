package joinstrategy;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B")
//원래 기본으로 자식 테이블은 부모 테이블의 ID 컬럼명을 그대로 사용 하는데,
// 자식 테이블의 기본 키 컬럼명을 변경 하고 싶을 때 사용 -> BOOK_ID 로 변경 (DDL)
@PrimaryKeyJoinColumn(name = "BOOK_ID")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Book extends Item{
    private String author;
    private String isbn;
}

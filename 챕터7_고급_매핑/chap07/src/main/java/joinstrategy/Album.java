package joinstrategy;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//엔티티를 저장할 때 구분 컬럼에 입력할 값을 지정-> A라고 저장됨
@DiscriminatorValue("A")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Album extends Item {
    private String artist;
}

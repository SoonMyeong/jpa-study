package mappedsuperclass;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
//@AttributeOverride : 부모에게 물려받은 매핑 정보 재정의
//@AttributeOverrides : AttributeOverride 둘 이상 재정의
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID")),
        @AttributeOverride(name = "name", column = @Column(name = "MEMBER_NAME"))
})
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Member extends BaseEntity {
    private String email;
}

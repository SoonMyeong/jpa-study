package mappedsuperclass;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@SuperBuilder
public class Seller extends BaseEntity {
    private String shopName;
}

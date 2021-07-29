package cascade.none;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cascadeNoneChild")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Parent parent;
}

package proxy.useLazy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "useLazyMember")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
}

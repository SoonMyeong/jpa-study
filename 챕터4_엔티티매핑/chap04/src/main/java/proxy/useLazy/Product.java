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
@Entity(name = "useLazyProduct")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();
}

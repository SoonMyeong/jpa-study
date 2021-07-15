package entity;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ",
        initialValue = 1 , allocationSize = 1
)
public class Board2 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "BOARD_SEQ_GENERATOR")

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

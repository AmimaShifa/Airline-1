package airlines.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by winio_000 on 2015-12-13.
 */
@Entity
@Table(name = "flight")
public class Flight implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    public Flight() {
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                '}';
    }
}

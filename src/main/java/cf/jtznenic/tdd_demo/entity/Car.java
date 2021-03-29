package cf.jtznenic.tdd_demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 */
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue

    private Long id;
    private String name;
    private String type;

    public Car() {
    }

    public Car(String name, String type) {
        this.name = name;
        this.type = type;
    }
}

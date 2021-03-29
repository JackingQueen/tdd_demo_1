package cf.jtznenic.tdd_demo.dao;

import cf.jtznenic.tdd_demo.entity.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 *
 */
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository respository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void getCar_returnCarDetails() {
        // This will get the test entity manager to insert the object to the database
        // and get recreate it.
        // if were to use jpa save method, then you are just testing on the cache but not
        // the actual persistence

        Car savedCar = entityManager.persistAndFlush(new Car("prius", "hybird"));
        Car car = respository.findByName("prius");

        Assertions.assertThat(car.getName()).isEqualTo("prius");
        Assertions.assertThat(car.getType()).isEqualTo("hybird");
    }
}
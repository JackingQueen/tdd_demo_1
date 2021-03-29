package cf.jtznenic.tdd_demo.dao;

import cf.jtznenic.tdd_demo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByName(String name);
}

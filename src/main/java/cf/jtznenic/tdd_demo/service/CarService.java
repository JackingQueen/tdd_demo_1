package cf.jtznenic.tdd_demo.service;

import cf.jtznenic.tdd_demo.dao.CarRepository;
import cf.jtznenic.tdd_demo.entity.Car;
import cf.jtznenic.tdd_demo.expception.CarNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *
 */
@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Cacheable("cars")
    public Car getCarDetails(String name) {
        Car car = carRepository.findByName(name);
        if (Objects.isNull(car)) {
            throw new CarNotFoundException();
        }
        return car;
    }
}

package cf.jtznenic.tdd_demo.service;

import cf.jtznenic.tdd_demo.dao.CarRepository;
import cf.jtznenic.tdd_demo.entity.Car;
import cf.jtznenic.tdd_demo.expception.CarNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 *
 */
@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    private CarService carService;

    @BeforeEach
    public void setUp() {
        carService = new CarService(carRepository);
    }

    @Test
    public void getCarDetails_returnsCarInfo() {
        given(carRepository.findByName("prius")).willReturn(new Car("prius", "hybird"));

        Car car = carService.getCarDetails("prius");

        assertThat(car.getName()).isEqualTo("prius");
        assertThat(car.getType()).isEqualTo("hybird");
    }

    @Test
    public void getCarDaetials_whenCarNotFound() {
        given(carRepository.findByName("prius")).willThrow((Class<? extends Throwable>) null);

        Assertions.assertThrows(CarNotFoundException.class, () -> {
            carService.getCarDetails("prius");
        });
    }
}
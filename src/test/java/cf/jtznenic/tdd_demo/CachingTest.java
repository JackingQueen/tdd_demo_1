package cf.jtznenic.tdd_demo;

import cf.jtznenic.tdd_demo.dao.CarRepository;
import cf.jtznenic.tdd_demo.entity.Car;
import cf.jtznenic.tdd_demo.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
@AutoConfigureCache
public class CachingTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void caching() {
        given(carRepository.findByName(anyString())).willReturn(new Car("prius", "hybird"));

        carService.getCarDetails("prius");
        carService.getCarDetails("prius");

        verify(carRepository, times(1)).findByName("prius");
    }
}

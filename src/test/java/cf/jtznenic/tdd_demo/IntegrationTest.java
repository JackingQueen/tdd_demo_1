package cf.jtznenic.tdd_demo;

import cf.jtznenic.tdd_demo.entity.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *集成测试
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCar_returnsCarDetails() {
        // arrange

        // act
        ResponseEntity<Car> response = restTemplate.getForEntity("/cars/prius", Car.class);

        // assert
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getName()).isEqualTo("prius");
        Assertions.assertThat(response.getBody().getType()).isEqualTo("hybird");
    }
}

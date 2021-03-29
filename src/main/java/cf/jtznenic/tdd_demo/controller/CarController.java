package cf.jtznenic.tdd_demo.controller;

import cf.jtznenic.tdd_demo.entity.Car;
import cf.jtznenic.tdd_demo.expception.CarNotFoundException;
import cf.jtznenic.tdd_demo.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Slf4j
@RestController
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/{name}")
    private Car getCar(@PathVariable String name) {
        return carService.getCarDetails(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void carNotFoundHandler(CarNotFoundException ex) {

    }
}

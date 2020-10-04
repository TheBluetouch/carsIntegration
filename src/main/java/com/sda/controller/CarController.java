package com.sda.controller;

import com.sda.domain.Car;
import com.sda.dto.CreateCarDto;
import com.sda.service.CarService;
import com.sda.view.CarView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

    private final RestTemplate restTemplate;

    private final CarService carService;

    public CarController(RestTemplate restTemplate, CarService carService) {
        this.restTemplate = restTemplate;
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CreateCarDto dto) {
        Car car = carService.create(dto);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping("/external/{userId}")
    public List<CarView> getCars(@PathVariable Long userId) {
        return carService.findForUser(userId);
    }


}

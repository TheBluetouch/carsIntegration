package com.sda.controller;

import com.sda.domain.Car;
import com.sda.dto.CreateCarDto;
import com.sda.service.CarService;
import com.sda.view.CarView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CreateCarDto dto) {
        Car car = carService.create(dto);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll(){
        List<Car> cars = carService.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/external/{userId}")
    public List<CarView> getCars(@PathVariable Long userId) {
        return carService.findForUser(userId);
    }
}

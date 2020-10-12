package com.sda.service;

import com.sda.domain.Car;
import com.sda.dto.CreateCarDto;
import com.sda.repository.CarRepository;
import com.sda.view.CarView;
import com.sda.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private static String USERS_URL = "http://localhost/8051/user/external/";

    private final CarRepository carRepository;

    private final RestTemplate restTemplate;


    @Autowired
    public CarService(CarRepository carRepository, RestTemplate restTemplate) {
        this.carRepository = carRepository;
        this.restTemplate = restTemplate;
    }

    public Car create(CreateCarDto dto) {
        Long userId = dto.getUserId();
        UserView userView = getUserFromExternalService(userId);

        Car car = Car.builder()
                .company(dto.getCompany())
                .model(dto.getModel())
                .age(dto.getAge())
                .userName(userView.getName())
                .userSurname(userView.getSurname())
                .userAge(userView.getAge())
                .build();

        carRepository.save(car);
        return car;
    }

    public UserView getUserFromExternalService(Long userId) {
        return restTemplate.getForObject(USERS_URL + userId, UserView.class);
    }

    public List<Car> findAll(){return carRepository.findAll();}

    public List<CarView> findForUser(Long userId) {
        return carRepository.findUserByUserId(userId)
                .stream()
                .map(c -> new CarView(c.getCompany(), c.getModel(), c.getAge()))
                .collect(Collectors.toList());
    }
}

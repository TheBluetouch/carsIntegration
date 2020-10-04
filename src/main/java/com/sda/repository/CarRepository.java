package com.sda.repository;

import com.sda.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findUserByUserId(Long userId);
}

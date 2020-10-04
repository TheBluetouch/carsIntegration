package com.sda.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    private Long UserId;

    private String company;

    private String model;

    private Integer age;

    private String userName;

    private String userSurname;

    private Integer userAge;


}

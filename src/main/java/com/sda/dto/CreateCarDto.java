package com.sda.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateCarDto {

    @NotNull
    private Long userId;

    @NotNull
    private String company;

    @NotNull
    private String model;

    @NotNull
    private Integer age;
}

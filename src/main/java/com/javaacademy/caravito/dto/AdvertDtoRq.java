package com.javaacademy.caravito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdvertDtoRq {
    private String brandName;
    private String color;
    private BigDecimal price;
}

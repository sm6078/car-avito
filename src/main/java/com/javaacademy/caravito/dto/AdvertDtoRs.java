package com.javaacademy.caravito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertDtoRs {
    private String uuid;
    private String brandName;
    private String color;
    private BigDecimal price;
}

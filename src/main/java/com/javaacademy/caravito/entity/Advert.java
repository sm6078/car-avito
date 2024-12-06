package com.javaacademy.caravito.entity;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;

/**
 * класс Объявление
 */

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Advert {
    @EqualsAndHashCode.Include
    private String uuid;
    @NonNull
    private String brandName;
    @NonNull
    private String color;
    @NonNull
    private BigDecimal price;
}

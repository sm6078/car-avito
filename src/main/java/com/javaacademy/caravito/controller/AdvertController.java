package com.javaacademy.caravito.controller;

import com.javaacademy.caravito.dto.AdvertDtoRq;
import com.javaacademy.caravito.dto.AdvertDtoRs;
import com.javaacademy.caravito.exception.AdvertNotFoundException;
import com.javaacademy.caravito.service.AdvertService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/advert")
public class AdvertController {
    private final AdvertService advertService;

    @PostMapping
    public ResponseEntity<AdvertDtoRs> createAdvert(@RequestBody @NonNull AdvertDtoRq dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(advertService.create(dto));
    }

    @GetMapping("/brand")
    public ResponseEntity<?> getAdvertsByBrand(@RequestParam @NonNull String brand) {
        return ResponseEntity.status(HttpStatus.OK).body(advertService.findByBrandName(brand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        return advertService.deleteById(id)
                ? ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Advert successfully removed")
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("An error occurred while deleting the advert");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdvertById(@PathVariable @NonNull String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(advertService.findById(id));
        } catch (AdvertNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Advert with id: %s does not exist.", id));
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) BigDecimal price) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(advertService.findByFilter(brandName, color, price));
    }
}

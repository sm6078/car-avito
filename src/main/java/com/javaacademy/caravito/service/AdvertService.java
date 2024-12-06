package com.javaacademy.caravito.service;

import com.javaacademy.caravito.dto.AdvertDtoRq;
import com.javaacademy.caravito.dto.AdvertDtoRs;
import com.javaacademy.caravito.entity.Advert;
import com.javaacademy.caravito.exception.AdvertNotFoundException;
import com.javaacademy.caravito.repository.AdvertRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertDtoRs create(@NonNull AdvertDtoRq dto) {
        Advert advert = new Advert(dto.getBrandName(), dto.getColor(),
                dto.getPrice());
        advertRepository.create(advert);
        return convertToDtoRs(advert);
    }

    private AdvertDtoRs convertToDtoRs(@NonNull Advert advert) {
        return new AdvertDtoRs(advert.getUuid(), advert.getBrandName(), advert.getColor(),
                advert.getPrice());
    }

    public List<AdvertDtoRs> findByBrandName(@NonNull String brandName) {
        List<Advert> adverts = advertRepository.getByBrandName(brandName);
        return new ArrayList<>(adverts.stream()
                .map(this::convertToDtoRs)
                .toList());
    }

    public boolean deleteById(@NonNull String id) {
        return advertRepository.deleteById(id);
    }

    public AdvertDtoRs findById(@NonNull String id) {
        return advertRepository.getById(id)
                .map(this::convertToDtoRs)
                .orElseThrow(() -> new AdvertNotFoundException(
                        String.format("Advert with id: %s does not exist.", id)
                ));
    }

    public List<AdvertDtoRs> findByFilter(@NonNull String brandName,
                                          @NonNull String color,
                                          @NonNull BigDecimal price) {
        return advertRepository.getByFilter(brandName, color, price).stream()
                .map(this::convertToDtoRs)
                .toList();
    }

}

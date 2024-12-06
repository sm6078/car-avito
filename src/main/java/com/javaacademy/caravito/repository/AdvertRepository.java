package com.javaacademy.caravito.repository;

import com.javaacademy.caravito.entity.Advert;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

@Getter
@Component
public class AdvertRepository {
    private final Map<String, Advert> adverts = new HashMap<>();

    public void create(@NonNull Advert advert) {
        advert.setUuid(UUID.randomUUID().toString());
        adverts.put(advert.getUuid(), advert);
    }

    public Optional<Advert> getById(@NonNull String id) {
        return Optional.ofNullable(adverts.get(id));
    }

    public List<Advert> getByBrandName(@NonNull String brandName) {
        return adverts.values().stream()
                .filter(advert -> brandName.equals(advert.getBrandName()))
                .toList();
    }

    public boolean deleteById(@NonNull String id) {
        return adverts.remove(id) != null;
    }

    public ArrayList<Advert> getByFilter(String brandName, String color, BigDecimal price) {
        return new ArrayList<>(
                adverts.values().stream()
                        .filter(createPredicate(brandName, color, price))
                        .toList()
        );
    }

    private Predicate<Advert> createPredicate(String brandName, String color, BigDecimal price) {
        Predicate<Advert> brandNamePredicate = advert ->
                brandName == null || advert.getBrandName().equals(brandName);
        Predicate<Advert> colorPredicate = advert ->
                color == null || advert.getColor().equals(color);
        Predicate<Advert> pricePredicate = advert ->
                price == null || advert.getPrice().compareTo(price) == 0;
        return brandNamePredicate
                .and(pricePredicate)
                .and(colorPredicate);
    }
}

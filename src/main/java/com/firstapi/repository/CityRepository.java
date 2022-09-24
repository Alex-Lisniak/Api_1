package com.firstapi.repository;

import com.firstapi.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends CrudRepository<City, UUID> {


    Optional<City> getCityByName(String name);

    void deleteCityByName(String name);
}

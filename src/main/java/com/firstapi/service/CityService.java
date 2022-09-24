package com.firstapi.service;

import com.firstapi.dto.CityDto;
import com.firstapi.entity.AuthKey;
import com.firstapi.entity.City;

import java.util.UUID;


public interface CityService {

    AuthKey getKey();

    CityDto getCityByName(UUID key, String cityName);

    CityDto updateCityById(UUID key, UUID cityId, String cityName );

    CityDto createCity(UUID key, String cityName);

    void deleteCityByName(UUID key, String cityName);

}

package com.firstapi.service.impl;

import com.firstapi.dto.CityDto;
import com.firstapi.entity.AuthKey;
import com.firstapi.entity.City;
import com.firstapi.exception.CityNotFoundException;
import com.firstapi.exception.KeyIsNotValidException;
import com.firstapi.mapper.CityMapper;
import com.firstapi.repository.AuthKeyRepository;
import com.firstapi.repository.CityRepository;
import com.firstapi.service.CityService;
import com.firstapi.validation.AuthKeyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    private final AuthKeyValidator authKeyValidator;

    private final AuthKeyRepository authKeyRepository;

    @Transactional(readOnly = true)
    public AuthKey getKey(){
        List <AuthKey> list = new ArrayList<>();
        authKeyRepository.findAll().forEach(list::add);
        return list.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDto getCityByName(UUID key, String cityName){
        if(authKeyValidator.validate(key)) {
            return cityMapper.INSTANCE.cityToDto(cityRepository.getCityByName(cityName).orElseThrow( () -> new CityNotFoundException("City with " + cityName + " name wasn`t found")));
        } else throw new KeyIsNotValidException("Key is not valid " + + new Date().getTime() );
    }

    @Override
    @Transactional
    public CityDto updateCityById(UUID key, UUID cityId, String cityName) {
        if(authKeyValidator.validate(key)) {
            City city = cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException("City with " + cityId + " id wasn`t found"));
            city.setName(cityName);
            cityRepository.save(city);
            return cityMapper.INSTANCE.cityToDto(city);
        } else throw new KeyIsNotValidException("Key is not valid");
    }

    @Override
    @Transactional
    public CityDto createCity(UUID key, String cityName) {
        if(authKeyValidator.validate(key)) {
            City newCity = new City(UUID.randomUUID(), cityName);
            cityRepository.save(newCity);
            return cityMapper.INSTANCE.cityToDto(newCity);
        } else throw new KeyIsNotValidException("Key is not valid");
    }

    @Override
    @Transactional
    public void deleteCityByName(UUID key, String cityName) {
        if(authKeyValidator.validate(key)) {
            cityRepository.deleteCityByName(cityName);
        } else throw new KeyIsNotValidException("Key is not valid");
    }
}

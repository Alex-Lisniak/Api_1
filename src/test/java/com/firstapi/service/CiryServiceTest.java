package com.firstapi.service;

import com.firstapi.dto.CityDto;
import com.firstapi.entity.City;
import com.firstapi.mapper.CityMapper;
import com.firstapi.repository.CityRepository;
import com.firstapi.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CiryServiceTest {
    @InjectMocks
    private CityServiceImpl cityService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;


    @Test
    public void getCityByName(){
        String cityName = "Berlin";
        City city = new City(UUID.randomUUID(), cityName);
        CityDto cityDto = new CityDto(cityName);
        when(cityMapper.cityToDto(any())).thenReturn(cityDto);
        when(cityRepository.getCityByName(cityName)).thenReturn(Optional.ofNullable(city));
        CityDto actualCityDto = cityMapper.cityToDto(cityRepository.getCityByName(cityName).get());
        assertEquals(actualCityDto, cityDto);

    }
}

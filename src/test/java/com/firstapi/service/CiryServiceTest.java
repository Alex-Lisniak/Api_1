package com.firstapi.service;

import com.firstapi.dto.CityDto;
import com.firstapi.entity.City;
import com.firstapi.exception.KeyIsNotValidException;
import com.firstapi.mapper.CityMapper;
import com.firstapi.repository.CityRepository;
import com.firstapi.service.impl.CityServiceImpl;
import com.firstapi.validation.AuthKeyValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CiryServiceTest {

    @Mock
    private AuthKeyValidator authKeyValidator;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;


    @Test
    public void getCityByNameWithValidKey(){
        UUID keyId = UUID.randomUUID();
        String cityName = "Berlin";
        City city = new City(UUID.randomUUID(), cityName);
        CityDto cityDto = new CityDto(cityName);
        when(authKeyValidator.validate(keyId)).thenReturn(true);
        when(cityMapper.cityToDto(any())).thenReturn(cityDto);
        when(cityRepository.getCityByName(cityName)).thenReturn(Optional.ofNullable(city));
        if(authKeyValidator.validate(keyId)) {
            CityDto actualCityDto = cityMapper.cityToDto(cityRepository.getCityByName(cityName).get());
            assertEquals(actualCityDto, cityDto);
        }

    }
    @Test
    public void getCityByNameWithoutValidKey(){
        UUID keyId = UUID.randomUUID();
        String cityName = "Berlin";
        CityDto cityDto = new CityDto(cityName);
        when(authKeyValidator.validate(keyId)).thenReturn(false);


        assertThrows(KeyIsNotValidException.class, () ->{

            if(authKeyValidator.validate(keyId)) {
                CityDto actualCityDto = cityMapper.cityToDto(cityRepository.getCityByName(cityName).get());
                assertEquals(actualCityDto, cityDto);
            }  else throw new KeyIsNotValidException("Key is not valid " +  new Date().getTime() );

        });

    }
}

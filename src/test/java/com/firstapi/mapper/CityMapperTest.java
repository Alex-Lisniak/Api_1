package com.firstapi.mapper;

import com.firstapi.dto.CityDto;
import com.firstapi.entity.City;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CityMapperTest {
    @Test
    void cityToDto(){
        City city = new City(UUID.randomUUID(), "Frankfurt");
        CityDto cityDto = CityMapper.INSTANCE.cityToDto(city);


        assertThat(cityDto.getName()).isEqualTo(city.getName());
    }
}

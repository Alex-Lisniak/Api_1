package com.firstapi.mapper;

import com.firstapi.dto.CityDto;
import com.firstapi.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDto cityToDto (City city);

}

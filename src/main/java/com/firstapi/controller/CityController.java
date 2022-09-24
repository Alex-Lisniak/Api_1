package com.firstapi.controller;

import com.firstapi.dto.CityDto;
import com.firstapi.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @GetMapping("/key")
    public UUID getKey(){
        return cityService.getKey().getId();
    }


 // todo - provide methods if different cities has equal name
 // todo - replace post methods

    @GetMapping("/{cityName}")
    public boolean getCityByName(@RequestHeader(value = "key") UUID key, @PathVariable String cityName){
            return !(cityService.getCityByName(key, cityName).getName().isEmpty());
    }


    @Operation(summary = "create city passing all fields")
    @PostMapping("/new/{cityName}")
    public CityDto createNewCity(@RequestHeader(value = "key") UUID key, @PathVariable String cityName){
            return cityService.createCity(key, cityName);
    }

    @Operation(summary = "update city passing all fields")
    @PostMapping("/update/{cityId}/{cityName}")
    public CityDto updateCityById(@RequestHeader(value = "key") UUID key, @PathVariable UUID cityId, @PathVariable String cityName){
            return cityService.updateCityById(key, cityId, cityName);
    }

    @PostMapping("/{cityName}")
    public void deleteCityByName(@RequestHeader(value = "key") UUID key, @PathVariable String cityName) {
            cityService.deleteCityByName(key, cityName);
    }
}

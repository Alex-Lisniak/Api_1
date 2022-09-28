package com.firstapi.controller;

import com.firstapi.entity.AuthKey;
import com.firstapi.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityControllerTest {
    @Mock
    private CityServiceImpl cityService;

    @Test
    public void getKey(){
       UUID key = UUID.randomUUID();
       AuthKey authKey = new AuthKey(key, new Date().getTime());

       when(cityService.getKey()).thenReturn(authKey);
       UUID actualKey = cityService.getKey().getId();
       assertEquals(actualKey,key);

    }

    @Test
    public void getCityByName(){


    }
}

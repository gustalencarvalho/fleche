package com.api.fleche.services.Impl;

import com.api.fleche.model.dtos.LocationRegisterDto;
import com.api.fleche.model.Location;
import com.api.fleche.repository.LocationRepository;
import com.api.fleche.service.LocationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @Test
    @DisplayName("Cria cadastro do bar")
    LocationRegisterDto criaCadastroDeUmBarBar(LocationRegisterDto bar) {
        Location registro = locationService.registerBar(bar);
        return bar;
    }
}
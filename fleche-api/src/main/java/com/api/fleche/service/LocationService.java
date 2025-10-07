package com.api.fleche.service;

import com.api.fleche.model.Location;
import com.api.fleche.model.dtos.LocationRegisterDto;
import com.api.fleche.model.dtos.LocationDto;
import com.api.fleche.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location registerBar(LocationRegisterDto locationRegisterDto) {
        var location = new Location();
        BeanUtils.copyProperties(locationRegisterDto, location);
        return locationRepository.save(location);
    }

    public Location findByQrCode(String qrCode) {
        Optional<Location> location = locationRepository.findByQrCode(qrCode);
        return location.get();
    }

    public List<LocationDto> findAll() {
        return locationRepository.findAll()
                .stream()
                .map(location -> new LocationDto(
                        location.getId(),
                        location.getName(),
                        location.getAddress(),
                        location.getDistrict(),
                        location.getCity(),
                        location.getQrCode(),
                        location.getCoordinate(),
                        null))
                .collect(Collectors.toList());
    }

}

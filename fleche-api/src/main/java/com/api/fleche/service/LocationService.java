package com.api.fleche.service;

import com.api.fleche.model.Location;
import com.api.fleche.model.dtos.LocationDto;
import com.api.fleche.model.dtos.LocationRegisterDto;
import com.api.fleche.model.exception.LocationNotFoundException;
import com.api.fleche.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationDto registerBar(LocationRegisterDto locationRegisterDto) {
        var location = new Location();
        BeanUtils.copyProperties(locationRegisterDto, location);
        var locationSave = locationRepository.save(location);
        return new LocationDto(
                locationSave.getId(),
                locationSave.getName(),
                locationSave.getAddress(),
                locationSave.getDistrict(),
                locationSave.getCity(),
                locationSave.getQrCode(),
                locationSave.getCoordinate()
        );
    }

    public Location findByQrCode(String qrCode) {
        return locationRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new LocationNotFoundException("Location not found for QR code: " + qrCode));
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
                        location.getCoordinate()))
                .collect(Collectors.toList());
    }

}

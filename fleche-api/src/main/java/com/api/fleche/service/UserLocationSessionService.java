package com.api.fleche.service;

import com.api.fleche.dao.UserLocationSessionDao;
import com.api.fleche.enums.StatusUserLocation;
import com.api.fleche.model.Location;
import com.api.fleche.model.User;
import com.api.fleche.model.UserLocationSession;
import com.api.fleche.model.dtos.LocationDto;
import com.api.fleche.model.dtos.UserLocationDto;
import com.api.fleche.model.dtos.UserLocationSessionDto;
import com.api.fleche.model.exception.LocationNotFoundException;
import com.api.fleche.repository.UserLocationSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.api.fleche.infra.security.SecurityFilter.getAuthenticationUserId;

@Service
@RequiredArgsConstructor
public class UserLocationSessionService {

    private final UserLocationSessionRepository userLocationSessionRepository;
    private final UserLocationSessionDao userLocationSessionDao;
    private final LocationService locationService;
    private final UserService userService;

    public boolean findByUserIdAndDataExpireAfter(Long userId) {
        return userLocationSessionRepository.findByUserIdAndDateExpiresAfter(userId, LocalDateTime.now()).isPresent();
    }

    public String findByStatusUserLocation(Long userId) {
        return userLocationSessionRepository.findByUserId(userId);
    }

    public void checkin(String qrCode) {
        var status = validatorStatus(getAuthenticationUserId());
        var location = validateLocation(qrCode);
        var user = validatorUser(getAuthenticationUserId());
        var userLocationSessaoModel = new UserLocationSession();
        userLocationSessaoModel.setLocation(location);
        userLocationSessaoModel.setUser(user);
        userLocationSessaoModel.setDateActive(LocalDateTime.now(ZoneId.of("UTC")));
        userLocationSessaoModel.setDateExpires(LocalDateTime.now().plusHours(4));
        userLocationSessaoModel.setStatusUserLocation(StatusUserLocation.ONLINE);

        if (status == null) {
            save(userLocationSessaoModel);
        } else {
            userLocationSessionRepository.checkinOrCheckout(StatusUserLocation.ONLINE.name(), location.getId(), user.getId());
        }
    }

    public void checkout(Long userId) {
        var user = userService.findById(userId);
        var location = findByLocationId(userId);
        userLocationSessionRepository.checkinOrCheckout(StatusUserLocation.OFFLINE.name(), location, userId);
    }

    public Long findByLocationId(Long userId) {
        Long locationId = userLocationSessionRepository.findByLocationId(userId);
        if (locationId == null) {
            throw new LocationNotFoundException("Location not found");
        }
        return locationId;
    }

    public void save(UserLocationSession userLocationSession) {
        userLocationSessionRepository.save(userLocationSession);
    }

    public String qrCodeBar(Long locationId) {
        return userLocationSessionRepository.qrCodeBar(locationId);
    }

    public List<LocationDto> listTotalUserBar() {
        return userLocationSessionDao.listarTotalUsuariosPorBar(getAuthenticationUserId());
    }

    public List<UserLocationDto> usersOnlineList() {
        var user = userService.findById(getAuthenticationUserId());
        var location = findByLocationId(user.getId());
        String qrCode = qrCodeBar(location);
        return userLocationSessionDao.usuariosParaListar(qrCode, getAuthenticationUserId());
    }

    public String verifyIfUserOnline(Long userId) {
        return userLocationSessionRepository.verifyIfUserOnline(userId);
    }

    private String validatorStatus(Long userId) {
        return findByStatusUserLocation(userId);
    }

    private Location validateLocation(String qrCode) {
        return locationService.findByQrCode(qrCode);
    }

    private User validatorUser(Long userId) {
        return userService.findById(userId);
    }
}

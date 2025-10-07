package com.api.fleche.service;

import com.api.fleche.model.ProfileUser;
import com.api.fleche.model.User;
import com.api.fleche.model.dtos.ProfileUserDto;
import com.api.fleche.repository.ProfilerUserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileUserService {

    private final ProfilerUserRespository profilerUserRespository;
    private final UserService userService;

    public void saveProfile(ProfileUserDto profileUserDto, MultipartFile pic) throws IOException {
        var user = userService.findById(profileUserDto.getId());
        var profileUser = new ProfileUser();
        profileUser.setGender(profileUserDto.getGender().name());
        profileUser.setBio(profileUserDto.getBio());
        profileUser.setPreferences(profileUserDto.getPreferences().name());
        if (pic != null && !pic.isEmpty()) {
            profileUser.setPicture(pic.getBytes());
        }
        profileUser.setUserId(user.get());
        profilerUserRespository.save(profileUser);
    }

    public Optional<ProfileUser> findByUserId(Optional<User> user) {
        return profilerUserRespository.findByUserId(user);
    }

}

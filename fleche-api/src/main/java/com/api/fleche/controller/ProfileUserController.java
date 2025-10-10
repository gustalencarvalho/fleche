package com.api.fleche.controller;

import com.api.fleche.model.ProfileUser;
import com.api.fleche.model.dtos.ProfileRegisterRequest;
import com.api.fleche.model.dtos.ProfileUserDto;
import com.api.fleche.service.ProfileUserService;
import com.api.fleche.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileUserController {

    private final ProfileUserService profileUserService;
    private final UserService userService;

    @GetMapping("/{id}/picture")
    public ResponseEntity<byte[]> getFoto(@PathVariable Long id) {
        var user = userService.findById(id);
        Optional<ProfileUser> profileUser = profileUserService.findByUserId(user);
        if (profileUser.isPresent() && profileUser.get().getPicture() != null) {
            byte[] imagem = profileUser.get().getPicture();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imagem, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Registra novo usuário com foto de perfil",
            description = "Recebe o JSON de perfil e uma imagem opcional"
    )
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> register(@ModelAttribute ProfileRegisterRequest request) throws IOException {
        ProfileUserDto dto = new ObjectMapper().readValue(request.getProfile(), ProfileUserDto.class);
        profileUserService.saveProfile(dto, request.getPicture());
        return ResponseEntity.ok().build();
    }


}

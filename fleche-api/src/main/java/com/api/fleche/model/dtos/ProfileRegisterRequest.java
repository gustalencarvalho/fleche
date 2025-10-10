package com.api.fleche.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRegisterRequest {

    @Schema(type = "string", format = "binary", description = "Foto de perfil")
    private MultipartFile picture;

    @Schema(description = "Dados do usuário em formato JSON")
    private String  profile;
}

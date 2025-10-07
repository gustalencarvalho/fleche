package com.api.fleche.model.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLocationSessionDto {

    @NotNull(message = "User id required")
    private Long userId;

    @NotNull(message = "QR CODE required")
    private String qrCode;

}


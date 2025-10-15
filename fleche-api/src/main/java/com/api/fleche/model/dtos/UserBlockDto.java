package com.api.fleche.model.dtos;

import com.api.fleche.enums.StatusBlockade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class UserBlockDto {
    @NotNull(message = "Id user is required")
    private Long userId;

    @NotNull(message = "Id user blockade is required")
    private Long userIdBlockade;

    private String name;

    private String status;

    @NotBlank(message = "Reason is required")
    private String reason;

    public UserBlockDto() {}

    public UserBlockDto(String name, String status, String reason) {
        this.name = name;
        this.status = status;
        this.reason = reason;
    }
}

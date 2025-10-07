package com.api.fleche.model.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserUpdateDto {
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String preferences;
}

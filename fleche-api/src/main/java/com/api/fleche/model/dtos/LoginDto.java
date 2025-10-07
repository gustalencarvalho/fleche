package com.api.fleche.model.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    public LoginDto(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}

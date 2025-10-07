package com.api.fleche.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String status;
    private String statusUserBar;
    private String nameBar;

}

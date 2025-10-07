package com.api.fleche.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDto {
    private Long id;
    private String name;
    private String address;
    private String district;
    private String city;
    private String qrCode;
    private List<String> coordinate;
    private Long usersOnline;
}

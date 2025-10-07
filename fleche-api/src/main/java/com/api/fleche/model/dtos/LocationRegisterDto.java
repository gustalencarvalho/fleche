package com.api.fleche.model.dtos;

import com.api.fleche.enums.States;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class LocationRegisterDto {

    @NotBlank(message = "Bar name is required")
    @Size(max = 150, message = "The bar name must have a maximum of 150 characters")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "State is required")
    private States state;

    @NotEmpty(message = "Coordinate is required")
    private List<String> coordinate;

}

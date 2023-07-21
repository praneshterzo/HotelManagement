package com.example.hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDetailsDto {
    private String name;
    private String address;
    private String gender;
    private Boolean isAdult;
}

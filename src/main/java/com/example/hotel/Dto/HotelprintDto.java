package com.example.hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelprintDto {
    private int id;
    private String hotelName;
    private String city;
    private String nearbyspots;
}

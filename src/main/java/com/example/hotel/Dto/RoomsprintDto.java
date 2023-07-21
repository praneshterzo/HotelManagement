package com.example.hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomsprintDto {
    private int id;
    private String roomtype;
    private int noofpersons;
    private String services;
}

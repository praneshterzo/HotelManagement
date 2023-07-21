package com.example.hotel.Dto;

import com.example.hotel.Entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean services;
    private int roomId;
    private     List<PassengerDetailsDto>  passengerDetailsDtos;
}

package com.example.hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingStatus {
    private String username;
    private int roomNo;
    private int bookId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean services;
    private  boolean isBooked;
    private boolean isPaid;
    private double cost;
    private int paymentid;
}

package com.example.hotel.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean services;
    private  boolean isBooked;
    private boolean isPaid;
    @OneToOne(mappedBy = "book")
    private Payment payments;
    @ManyToOne
    @JsonBackReference
    private Rooms roomsList;
    @ManyToOne
    @JsonBackReference
    private User user;
    @OneToMany
    @JsonManagedReference
    private List<PassengerDetails> passengerDetails;
}

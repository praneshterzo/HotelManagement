package com.example.hotel.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rooms")

public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roomtype;
    private int noofpersons;
    private String services;
    @ManyToOne
    @JsonBackReference
    private Hotels hotels;
    @OneToMany(mappedBy = "roomsList")
    @JsonManagedReference
    private List<Booking> booking;
}

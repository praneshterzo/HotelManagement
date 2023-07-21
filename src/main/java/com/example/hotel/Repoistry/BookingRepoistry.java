package com.example.hotel.Repoistry;

import com.example.hotel.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepoistry extends JpaRepository<Booking,Integer>{
    public Booking findById(int id);
}

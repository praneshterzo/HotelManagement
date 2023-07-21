package com.example.hotel.Repoistry;

import com.example.hotel.Entity.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepoistry extends JpaRepository<Hotels,Integer> {
    public List<Hotels> findByCity(String city);

    public Hotels findById(int id);
}

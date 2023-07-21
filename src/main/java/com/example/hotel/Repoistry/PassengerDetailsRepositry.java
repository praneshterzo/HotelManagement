package com.example.hotel.Repoistry;

import com.example.hotel.Entity.PassengerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerDetailsRepositry extends JpaRepository<PassengerDetails,Integer> {
}

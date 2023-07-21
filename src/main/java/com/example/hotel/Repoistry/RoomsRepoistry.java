package com.example.hotel.Repoistry;

import com.example.hotel.Entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepoistry extends JpaRepository<Rooms,Integer> {
        public Rooms findById(int id);
}

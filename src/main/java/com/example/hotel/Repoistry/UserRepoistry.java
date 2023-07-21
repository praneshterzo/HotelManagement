package com.example.hotel.Repoistry;

import com.example.hotel.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoistry extends JpaRepository<User,Integer> {

    public User findByEmail(String email);
}

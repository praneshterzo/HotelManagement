package com.example.hotel.Repoistry;

import com.example.hotel.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepoistry extends JpaRepository<Payment,Integer> {
    public Payment findPaymentById(int id);
}

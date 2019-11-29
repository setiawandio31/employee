package com.example.employee.repository;

import com.example.employee.model.entity.PresentaseGaji;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentaseGajiRepository extends JpaRepository<PresentaseGaji, Long> {
    
}
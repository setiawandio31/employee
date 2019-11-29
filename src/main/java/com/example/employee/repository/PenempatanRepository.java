package com.example.employee.repository;

import com.example.employee.model.entity.Penempatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenempatanRepository extends JpaRepository<Penempatan, Long> {
    
}
package com.example.employee.repository;

import com.example.employee.model.entity.Agama;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgamaRepository extends JpaRepository<Agama, Long> {
    
}
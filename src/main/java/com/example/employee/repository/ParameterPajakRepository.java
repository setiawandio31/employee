package com.example.employee.repository;

import com.example.employee.model.entity.ParameterPajak;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterPajakRepository extends JpaRepository<ParameterPajak, Long> {
    
}
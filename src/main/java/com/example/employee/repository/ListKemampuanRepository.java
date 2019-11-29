package com.example.employee.repository;

import com.example.employee.model.entity.ListKemampuan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListKemampuanRepository extends JpaRepository<ListKemampuan, Long> {
    
}
package com.example.employee.repository;

import com.example.employee.model.entity.KategoriKemampuan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriKemampuanRepository extends JpaRepository<KategoriKemampuan, Long> {
    
}
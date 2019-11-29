package com.example.employee.repository;

import com.example.employee.model.entity.Pendapatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendapatanRepository extends JpaRepository<Pendapatan, Long> {

}
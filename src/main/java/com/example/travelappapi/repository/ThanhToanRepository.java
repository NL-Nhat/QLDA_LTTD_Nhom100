package com.example.travelappapi.repository;

import com.example.travelappapi.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer> {
    List<ThanhToan> findByMaDatTour(Integer maDatTour);
}


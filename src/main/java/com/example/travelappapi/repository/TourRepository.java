package com.example.travelappapi.repository;

import com.example.travelappapi.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer> {
    List<Tour> findByTrangThai(String trangThai); // ACTIVE tours
    List<Tour> findByMaDanhMucAndTrangThai(Integer maDanhMuc, String trangThai);
}


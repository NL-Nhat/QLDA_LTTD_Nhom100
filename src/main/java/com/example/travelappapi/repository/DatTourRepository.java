package com.example.travelappapi.repository;

import com.example.travelappapi.entity.DatTour;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DatTourRepository extends JpaRepository<DatTour, Integer> {
    List<DatTour> findByNguoiDung_MaNguoiDung(Integer maNguoiDung);
}


package com.example.travelappapi.repository;

import com.example.travelappapi.entity.HinhAnhTour;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HinhAnhTourRepository extends JpaRepository<HinhAnhTour, Integer> {
    List<HinhAnhTour> findByTour_MaTour(Integer maTour);
}


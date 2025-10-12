package com.example.travelappapi.repository;

import com.example.travelappapi.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {
    List<DanhGia> findByMaTour(Integer maTour);
}


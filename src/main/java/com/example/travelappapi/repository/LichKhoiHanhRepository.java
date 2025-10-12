package com.example.travelappapi.repository;

import com.example.travelappapi.entity.LichKhoiHanh;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LichKhoiHanhRepository extends JpaRepository<LichKhoiHanh, Integer> {
    List<LichKhoiHanh> findByMaTourAndTrangThai(Integer maTour, String trangThai);
}


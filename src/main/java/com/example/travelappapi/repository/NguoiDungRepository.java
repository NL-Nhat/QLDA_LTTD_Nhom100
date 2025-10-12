package com.example.travelappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelappapi.entity.NguoiDung;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
    Optional<NguoiDung> findByEmail(String email);
}


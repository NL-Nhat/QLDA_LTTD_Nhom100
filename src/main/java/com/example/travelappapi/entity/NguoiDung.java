package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "NguoiDung")
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_nguoi_dung;

    @Column(nullable = false, unique = true)
    private String ten_dang_nhap;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String mat_khau_hash;

    @Column(nullable = false)
    private String ho_ten;

    private String so_dien_thoai;
    private String dia_chi;
    private LocalDate ngay_sinh;
    private String gioi_tinh;
    private String url_avatar;
    private String vai_tro;
    private String trang_thai;
    private LocalDateTime thoi_gian_tao;
    private LocalDateTime thoi_gian_cap_nhat;
    private LocalDateTime dang_nhap_cuoi;
}

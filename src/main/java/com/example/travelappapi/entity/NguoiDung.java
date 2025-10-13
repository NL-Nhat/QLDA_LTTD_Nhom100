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
    private Integer maNguoiDung;

    @Column(nullable = false, unique = true)
    private String tenDangNhap;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String matKhauHash;

    @Column(nullable = false)
    private String hoTen;

    private String soDienThoai;
    private String diaChi;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String urlAvatar;
    private String vaiTro;
    private String trangThai;
    private LocalDateTime thoiGianTao;
    private LocalDateTime thoiGianCapNhat;
    private LocalDateTime dangNhapCuoi;
}
package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "Tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTour;

    @Column(nullable = false)
    private String tenTour;

    @Column(nullable = false, unique = true)
    private String maTourCode;

    @ManyToOne
    @JoinColumn(name = "maDanhMuc")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "maDiemDen")
    private DiemDen diemDen;

    private String moTa;
    private String thoiGian;
    private Integer kichThuocNhomToiDa;
    private Double gia;
    private Double tyLeGiamGia;
    private String urlHinhAnhChinh;
    private LocalTime thoiGianBatDau;
    private LocalTime thoiGianKetThuc;
    private String ngonNguHuongDan;
    private String quyTac;
    private Double diemDanhGiaTrungBinh;
    private Integer soLuongDanhGia;
    private String trangThai;
    private Boolean noiBat;
    private LocalDateTime thoiGianTao;
    private LocalDateTime thoiGianCapNhat;

    @ManyToOne
    @JoinColumn(name = "taoBoi")
    private NguoiDung nguoiTao;
}
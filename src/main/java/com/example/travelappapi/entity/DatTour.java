package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "DatTour")
public class DatTour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maDatTour;

    @Column(nullable = false, unique = true)
    private String maDatTourCode;

    @ManyToOne
    @JoinColumn(name = "maNguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "maLich")
    private LichKhoiHanh lichKhoiHanh;

    private Integer soNguoiLon;
    private Integer soTreEm;
    private Double tongTien;
    private Double tienGiamGia;
    private Double tienThanhToan;
    private String trangThaiDatTour;
    private String trangThaiThanhToan;
    private String ghiChuKhachHang;
    private String ghiChuQuanTri;
    private LocalDateTime ngayDat;
    private LocalDateTime ngayXacNhan;
    private LocalDateTime ngayHuy;
    private String lyDoHuy;
    private LocalDateTime thoiGianHuy;
}
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
    private Integer ma_dat_tour;

    @Column(nullable = false, unique = true)
    private String ma_dat_tour_code;

    @ManyToOne
    @JoinColumn(name = "ma_nguoi_dung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "ma_lich")
    private LichKhoiHanh lichKhoiHanh;

    private Integer so_nguoi_lon;
    private Integer so_tre_em;
    private Double tong_tien;
    private Double tien_giam_gia;
    private Double tien_thanh_toan;
    private String trang_thai_dat_tour;
    private String trang_thai_thanh_toan;
    private String ghi_chu_khach_hang;
    private String ghi_chu_quan_tri;
    private LocalDateTime ngay_dat;
    private LocalDateTime ngay_xac_nhan;
    private LocalDateTime ngay_huy;
    private String ly_do_huy;
    private LocalDateTime thoi_gian_huy;
}


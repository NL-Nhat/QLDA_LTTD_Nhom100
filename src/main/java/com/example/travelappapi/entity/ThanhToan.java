package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ThanhToan")
public class ThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_thanh_toan;

    @ManyToOne
    @JoinColumn(name = "ma_dat_tour")
    private DatTour datTour;

    private String phuong_thuc_thanh_toan;
    private Double so_tien;
    private String ma_giao_dich;
    private String trang_thai_thanh_toan;
    private LocalDateTime ngay_thanh_toan;
    private LocalDateTime ngay_xu_ly;
    private String ly_do_that_bai;
    private String ghi_chu;
}


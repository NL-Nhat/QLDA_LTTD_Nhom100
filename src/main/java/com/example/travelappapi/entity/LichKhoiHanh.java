package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "LichKhoiHanh")
public class LichKhoiHanh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_lich;

    @ManyToOne
    @JoinColumn(name = "ma_tour")
    private Tour tour;

    private LocalDate ngay_khoi_hanh;
    private LocalDate ngay_tro_ve;
    private Integer so_cho_con_lai;
    private Integer so_cho_da_dat;
    private Double gia;
    private String trang_thai;
    private String ten_huong_dan_vien;
    private String so_dien_thoai_huong_dan_vien;
    private String diem_hen;
    private String ghi_chu_dac_biet;
    private LocalDateTime thoi_gian_tao;
}

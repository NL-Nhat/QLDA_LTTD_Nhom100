package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "DiemDen")
public class DiemDen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_diem_den;

    @Column(nullable = false)
    private String ten_diem_den;

    @Column(nullable = false)
    private String quoc_gia;

    @Column(nullable = false)
    private String thanh_pho;

    private String mo_ta;
    private String url_hinh_anh;
    private Double vi_do;
    private Double kinh_do;
    private Boolean trang_thai;
    private LocalDateTime thoi_gian_tao;
}


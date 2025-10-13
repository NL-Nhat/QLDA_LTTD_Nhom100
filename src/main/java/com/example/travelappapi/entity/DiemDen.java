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
    private Integer maDiemDen;

    @Column(nullable = false)
    private String tenDiemDen;

    @Column(nullable = false)
    private String quocGia;

    @Column(nullable = false)
    private String thanhPho;

    private String moTa;
    private String urlHinhAnh;
    private Double viDo;
    private Double kinhDo;
    private Boolean trangThai;
    private LocalDateTime thoiGianTao;
}
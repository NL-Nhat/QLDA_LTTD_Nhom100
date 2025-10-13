package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "DanhGia")
public class DanhGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maDanhGia;

    @ManyToOne
    @JoinColumn(name = "maTour")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "maNguoiDung")
    private NguoiDung nguoiDung;

    private Double diemSo;
    private String binhLuan;
    private LocalDateTime thoiGianTao;
}
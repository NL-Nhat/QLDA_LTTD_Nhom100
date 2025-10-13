package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "HinhAnhTour")
public class HinhAnhTour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maHinhAnh;

    @ManyToOne
    @JoinColumn(name = "maTour")
    private Tour tour;

    @Column(nullable = false)
    private String urlHinhAnh;

    private String tieuDeHinhAnh;
    private Boolean laChinh;
    private Integer thuTuHienThi;
    private LocalDateTime thoiGianTao;
}
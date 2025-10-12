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
    private Integer ma_hinh_anh;

    @ManyToOne
    @JoinColumn(name = "ma_tour")
    private Tour tour;

    @Column(nullable = false)
    private String url_hinh_anh;

    private String tieu_de_hinh_anh;
    private Boolean la_chinh;
    private Integer thu_tu_hien_thi;
    private LocalDateTime thoi_gian_tao;
}


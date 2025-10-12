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
    private Integer ma_danh_gia;

    @ManyToOne
    @JoinColumn(name = "ma_tour")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "ma_nguoi_dung")
    private NguoiDung nguoiDung;

    private Double diem_so;
    private String binh_luan;
    private LocalDateTime thoi_gian_tao;
}


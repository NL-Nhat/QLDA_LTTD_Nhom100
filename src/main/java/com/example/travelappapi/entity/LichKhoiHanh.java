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
    private Integer maLich;

    @ManyToOne
    @JoinColumn(name = "maTour")
    private Tour tour;

    private LocalDate ngayKhoiHanh;
    private LocalDate ngayTroVe;
    private Integer soChoConLai;
    private Integer soChoDaDat;
    private Double gia;
    private String trangThai;
    private String tenHuongDanVien;
    private String soDienThoaiHuongDanVien;
    private String diemHen;
    private String ghiChuDacBiet;
    private LocalDateTime thoiGianTao;
}
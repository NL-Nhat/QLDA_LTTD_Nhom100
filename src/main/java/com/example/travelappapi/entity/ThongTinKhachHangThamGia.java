package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ThongTinKhachHangThamGia")
public class ThongTinKhachHangThamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maThongTin;

    @ManyToOne
    @JoinColumn(name = "maDatTour", nullable = false)
    private DatTour datTour;

    @Column(nullable = false, length = 100)
    private String hoTen;

    private LocalDate ngaySinh;

    @Column(length = 15)
    private String soDienThoai;

    @Column(length = 10)
    private String gioiTinh;

    @Column(length = 255)
    private String diaChi;

    @Column(length = 500)
    private String ghiChu;

    @Column(name = "thoiGianTao", columnDefinition = "DATETIME2")
    private LocalDateTime thoiGianTao;
}
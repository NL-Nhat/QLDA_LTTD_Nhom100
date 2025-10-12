package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "Tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_tour;

    @Column(nullable = false)
    private String ten_tour;

    @Column(nullable = false, unique = true)
    private String ma_tour_code;

    @ManyToOne
    @JoinColumn(name = "ma_danh_muc")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "ma_diem_den")
    private DiemDen diemDen;

    private String mo_ta;
    private String thoi_gian;
    private Integer kich_thuoc_nhom_toi_da;
    private Double gia;
    private Double ty_le_giam_gia;
    private String url_hinh_anh_chinh;
    private LocalTime thoi_gian_bat_dau;
    private LocalTime thoi_gian_ket_thuc;
    private String ngon_ngu_huong_dan;
    private String quy_tac;
    private Double diem_danh_gia_trung_binh;
    private Integer so_luong_danh_gia;
    private String trang_thai;
    private Boolean noi_bat;
    private LocalDateTime thoi_gian_tao;
    private LocalDateTime thoi_gian_cap_nhat;

    @ManyToOne
    @JoinColumn(name = "tao_boi")
    private NguoiDung nguoiTao;
}

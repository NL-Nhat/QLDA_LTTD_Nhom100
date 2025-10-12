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
    private Integer ma_thong_tin;

    @ManyToOne
    @JoinColumn(name = "ma_dat_tour", nullable = false)
    private DatTour datTour;

    @Column(nullable = false, length = 100)
    private String ho_ten;

    private LocalDate ngay_sinh;

    @Column(length = 15)
    private String so_dien_thoai;

    @Column(length = 10)
    private String gioi_tinh; // Nam / Nu / Khac

    @Column(length = 255)
    private String dia_chi;

    @Column(length = 500)
    private String ghi_chu;

    @Column(name = "thoi_gian_tao", columnDefinition = "DATETIME2")
    private LocalDateTime thoi_gian_tao;
}


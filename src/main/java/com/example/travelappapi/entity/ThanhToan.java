package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ThanhToan")
public class ThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maThanhToan;

    @ManyToOne
    @JoinColumn(name = "maDatTour")
    private DatTour datTour;

    private String phuongThucThanhToan;
    private Double soTien;
    private String maGiaoDich;
    private String trangThaiThanhToan;
    private LocalDateTime ngayThanhToan;
    private LocalDateTime ngayXuLy;
    private String lyDoThatBai;
    private String ghiChu;
}
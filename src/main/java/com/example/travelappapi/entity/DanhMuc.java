package com.example.travelappapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "DanhMuc")
public class DanhMuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maDanhMuc;

    @Column(nullable = false, unique = true)
    private String tenDanhMuc;

    private String moTa;
    private String urlIcon;
    private Boolean trangThai;
    private LocalDateTime thoiGianTao;
}
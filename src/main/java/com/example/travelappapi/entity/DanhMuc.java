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
    private Integer ma_danh_muc;

    @Column(nullable = false, unique = true)
    private String ten_danh_muc;

    private String mo_ta;
    private String url_icon;
    private Boolean trang_thai;
    private LocalDateTime thoi_gian_tao;
}


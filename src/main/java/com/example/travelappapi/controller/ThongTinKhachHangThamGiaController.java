package com.example.travelappapi.controller;

import com.example.travelappapi.entity.ThongTinKhachHangThamGia;
import com.example.travelappapi.service.ThongTinKhachHangThamGiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/khach-tham-gia")
@RequiredArgsConstructor
public class ThongTinKhachHangThamGiaController {
    private final ThongTinKhachHangThamGiaService khachHangService;

    @PostMapping
    public ThongTinKhachHangThamGia addKhachThamGia(@RequestBody ThongTinKhachHangThamGia khach) {
        return khachHangService.addKhachThamGia(khach);
    }

    @GetMapping("/booking/{maDatTour}")
    public List<ThongTinKhachHangThamGia> getKhachByDatTour(@PathVariable Integer maDatTour) {
        return khachHangService.getKhachByDatTour(maDatTour);
    }
}


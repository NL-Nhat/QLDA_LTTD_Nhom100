package com.example.travelappapi.controller;

import com.example.travelappapi.entity.DanhGia;
import com.example.travelappapi.service.DanhGiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class DanhGiaController {
    private final DanhGiaService danhGiaService;

    @PostMapping
    public DanhGia createReview(@RequestBody DanhGia danhGia) {
        return danhGiaService.createReview(danhGia);
    }

    @GetMapping("/tour/{maTour}")
    public List<DanhGia> getReviewsByTour(@PathVariable Integer maTour) {
        return danhGiaService.getReviewsByTour(maTour);
    }
}


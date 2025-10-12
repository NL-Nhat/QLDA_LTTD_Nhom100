package com.example.travelappapi.controller;

import com.example.travelappapi.entity.Tour;
import com.example.travelappapi.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

    @GetMapping
    public List<Tour> getAllActiveTours() {
        return tourService.getAllActiveTours();
    }

    @GetMapping("/danhmuc/{maDanhMuc}")
    public List<Tour> getToursByDanhMuc(@PathVariable Integer maDanhMuc) {
        return tourService.getToursByDanhMuc(maDanhMuc);
    }

    @GetMapping("/{maTour}")
    public Tour getTourById(@PathVariable Integer maTour) {
        return tourService.getTourById(maTour);
    }
}

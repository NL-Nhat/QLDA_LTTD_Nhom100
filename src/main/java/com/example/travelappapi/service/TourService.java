package com.example.travelappapi.service;

import com.example.travelappapi.entity.Tour;
import com.example.travelappapi.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;

    public List<Tour> getAllActiveTours() {
        return tourRepository.findByTrangThai("ACTIVE");
    }

    public List<Tour> getToursByDanhMuc(Integer maDanhMuc) {
        return tourRepository.findByDanhMuc_MaDanhMucAndTrangThai(maDanhMuc, "ACTIVE");
    }

    public Tour getTourById(Integer maTour) {
        return tourRepository.findById(maTour).orElse(null);
    }
}


package com.example.travelappapi.service;

import com.example.travelappapi.entity.DanhGia;
import com.example.travelappapi.repository.DanhGiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DanhGiaService {
    private final DanhGiaRepository danhGiaRepository;

    public DanhGia createReview(DanhGia danhGia) {
        return danhGiaRepository.save(danhGia);
    }

    public List<DanhGia> getReviewsByTour(Integer maTour) {
        return danhGiaRepository.findByTour_MaTour(maTour);
    }
}

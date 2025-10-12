package com.example.travelappapi.service;

import com.example.travelappapi.entity.ThanhToan;
import com.example.travelappapi.repository.ThanhToanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThanhToanService {
    private final ThanhToanRepository thanhToanRepository;

    public ThanhToan createPayment(ThanhToan thanhToan) {
        return thanhToanRepository.save(thanhToan);
    }

    public List<ThanhToan> getPaymentsByDatTour(Integer maDatTour) {
        return thanhToanRepository.findByMaDatTour(maDatTour);
    }
}

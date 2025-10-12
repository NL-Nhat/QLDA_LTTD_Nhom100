package com.example.travelappapi.service;

import com.example.travelappapi.entity.DatTour;
import com.example.travelappapi.repository.DatTourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatTourService {
    private final DatTourRepository datTourRepository;

    public DatTour createBooking(DatTour datTour) {
        return datTourRepository.save(datTour);
    }

    public List<DatTour> getBookingsByUser(Integer maNguoiDung) {
        return datTourRepository.findByMaNguoiDung(maNguoiDung);
    }

    public DatTour getBookingById(Integer maDatTour) {
        return datTourRepository.findById(maDatTour).orElse(null);
    }
}


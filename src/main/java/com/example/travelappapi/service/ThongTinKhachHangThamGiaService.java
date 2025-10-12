package com.example.travelappapi.service;

import com.example.travelappapi.entity.ThongTinKhachHangThamGia;
import com.example.travelappapi.repository.ThongTinKhachHangThamGiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThongTinKhachHangThamGiaService {
    private final ThongTinKhachHangThamGiaRepository thongTinRepository;

    public ThongTinKhachHangThamGia addKhachThamGia(ThongTinKhachHangThamGia khach) {
        return thongTinRepository.save(khach);
    }

    public List<ThongTinKhachHangThamGia> getKhachByDatTour(Integer maDatTour) {
        return thongTinRepository.findByMaDatTour(maDatTour);
    }
}


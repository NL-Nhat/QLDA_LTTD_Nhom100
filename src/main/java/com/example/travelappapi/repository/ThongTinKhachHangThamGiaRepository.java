package com.example.travelappapi.repository;

import com.example.travelappapi.entity.ThongTinKhachHangThamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ThongTinKhachHangThamGiaRepository extends JpaRepository<ThongTinKhachHangThamGia, Integer> {
    List<ThongTinKhachHangThamGia> findByDatTour_MaDatTour(Integer maDatTour);
}

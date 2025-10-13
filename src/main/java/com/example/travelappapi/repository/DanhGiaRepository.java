package com.example.travelappapi.repository;

import com.example.travelappapi.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {
    // Sửa lỗi ở đây: Sử dụng findBy[Tên thuộc tính Entity]_[Tên ID trong Entity liên quan]
    List<DanhGia> findByTour_MaTour(Integer maTour);
}
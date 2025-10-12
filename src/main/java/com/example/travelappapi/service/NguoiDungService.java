package com.example.travelappapi.service;

import com.example.travelappapi.entity.NguoiDung;
import com.example.travelappapi.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NguoiDungService {

    private final NguoiDungRepository nguoiDungRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public NguoiDungService(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    /**
     * Đăng ký: nhận NguoiDung (trường mat_khau_hash chứa mật khẩu thô từ client),
     * kiểm tra trùng ten_dang_nhap / email, mã hóa mật khẩu và lưu.
     */
    public NguoiDung register(NguoiDung req) {
        // kiểm tra
        if (nguoiDungRepository.findByTenDangNhap(req.getTen_dang_nhap()).isPresent()) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }
        if (nguoiDungRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // mã hóa mật khẩu (client gửi trường mat_khau_hash = mật khẩu thô)
        String rawPassword = req.getMat_khau_hash();
        String hashed = passwordEncoder.encode(rawPassword);
        req.setMat_khau_hash(hashed);

        // thiết lập mặc định nếu cần
        if (req.getVai_tro() == null) req.setVai_tro("CUSTOMER");
        if (req.getTrang_thai() == null) req.setTrang_thai("ACTIVE");
        LocalDateTime now = LocalDateTime.now();
        req.setThoi_gian_tao(now);
        req.setThoi_gian_cap_nhat(now);

        return nguoiDungRepository.save(req);
    }

    /**
     * Đăng nhập: kiểm tra ten_dang_nhap tồn tại và khớp mật khẩu thô với mat_khau_hash.
     * Trả về NguoiDung (đã cập nhật dang_nhap_cuoi và thoi_gian_cap_nhat).
     */
    public NguoiDung login(String tenDangNhap, String plainPassword) {
        Optional<NguoiDung> opt = nguoiDungRepository.findByTenDangNhap(tenDangNhap);
        if (opt.isEmpty()) {
            throw new IllegalArgumentException("Sai tên đăng nhập hoặc mật khẩu");
        }
        NguoiDung user = opt.get();
        if (!passwordEncoder.matches(plainPassword, user.getMat_khau_hash())) {
            throw new IllegalArgumentException("Sai tên đăng nhập hoặc mật khẩu");
        }

        // cập nhật thời gian đăng nhập/cuối
        LocalDateTime now = LocalDateTime.now();
        user.setDang_nhap_cuoi(now);
        user.setThoi_gian_cap_nhat(now);
        nguoiDungRepository.save(user);

        return user;
    }
}

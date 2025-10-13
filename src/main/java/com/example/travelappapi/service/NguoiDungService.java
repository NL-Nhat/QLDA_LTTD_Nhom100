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
        if (nguoiDungRepository.findByTenDangNhap(req.getTenDangNhap()).isPresent()) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }
        if (nguoiDungRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // mã hóa mật khẩu (client gửi trường mat_khau_hash = mật khẩu thô)
        String rawPassword = req.getMatKhauHash();
        String hashed = passwordEncoder.encode(rawPassword);
        req.setMatKhauHash(hashed);

        // thiết lập mặc định nếu cần
        if (req.getVaiTro() == null) req.setVaiTro("CUSTOMER");
        if (req.getTrangThai() == null) req.setTrangThai("ACTIVE");
        LocalDateTime now = LocalDateTime.now();
        req.setThoiGianTao(now);
        req.setThoiGianCapNhat(now);

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
        if (!passwordEncoder.matches(plainPassword, user.getMatKhauHash())) {
            throw new IllegalArgumentException("Sai tên đăng nhập hoặc mật khẩu");
        }

        // cập nhật thời gian đăng nhập/cuối
        LocalDateTime now = LocalDateTime.now();
        user.setDangNhapCuoi(now);
        user.setThoiGianCapNhat(now);
        nguoiDungRepository.save(user);

        return user;
    }
}

package com.example.travelappapi.controller;

import com.example.travelappapi.entity.NguoiDung;
import com.example.travelappapi.service.NguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final NguoiDungService nguoiDungService;

    /**
     * Register: client gửi JSON tương ứng với entity NguoiDung,
     * trong đó trường mat_khau_hash chứa mật khẩu thô.
     * Trả về thông tin user (không lộ mat_khau_hash).
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NguoiDung req) {
        try {
            NguoiDung saved = nguoiDungService.register(req);
            return ResponseEntity.ok(safeUserResponse(saved));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", ex.getMessage()));
        }
    }

    /**
     * Login: client gửi JSON { "ten_dang_nhap": "...", "mat_khau": "..." }
     * Lưu ý: trường mat_khau (không phải mat_khau_hash) là mật khẩu thô.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String ten = body.get("ten_dang_nhap");
        String matKhau = body.get("mat_khau");
        if (ten == null || matKhau == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Thiếu ten_dang_nhap hoặc mat_khau"));
        }

        try {
            NguoiDung user = nguoiDungService.login(ten, matKhau);
            return ResponseEntity.ok(safeUserResponse(user));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", ex.getMessage()));
        }
    }

    /**
     * Chuyển NguoiDung -> Map để không trả về mat_khau_hash.
     */
    private Map<String, Object> safeUserResponse(NguoiDung u) {
        Map<String, Object> m = new HashMap<>();
        m.put("ma_nguoi_dung", u.getMaNguoiDung());
        m.put("ten_dang_nhap", u.getTenDangNhap());
        m.put("email", u.getEmail());
        m.put("ho_ten", u.getHoTen());
        m.put("so_dien_thoai", u.getSoDienThoai());
        m.put("dia_chi", u.getDiaChi());
        m.put("ngay_sinh", u.getNgaySinh());
        m.put("gioi_tinh", u.getGioiTinh());
        m.put("url_avatar", u.getUrlAvatar());
        m.put("vai_tro", u.getVaiTro());
        m.put("trang_thai", u.getTrangThai());
        m.put("thoi_gian_tao", u.getThoiGianTao());
        m.put("thoi_gian_cap_nhat", u.getThoiGianCapNhat());
        m.put("dang_nhap_cuoi", u.getDangNhapCuoi());
        return m;
    }
}


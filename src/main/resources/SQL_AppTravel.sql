-- =============================================
-- HỆ THỐNG QUẢN LÝ DU LỊCH - DATABASE DESIGN
-- SQL Server Database Schema
-- =============================================

-- Kiểm tra và xóa database nếu đã tồn tại
USE master;
GO

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'dbAppTravel')
BEGIN
    -- Đóng tất cả kết nối đến database
    ALTER DATABASE dbAppTravel SET MULTI_USER WITH ROLLBACK IMMEDIATE;
    -- Xóa database
    DROP DATABASE dbAppTravel;
END
GO

-- Tạo database mới
CREATE DATABASE dbAppTravel
COLLATE Vietnamese_CI_AS;
GO

-- Sử dụng database vừa tạo
USE dbAppTravel;
GO
ALTER DATABASE dbAppTravel SET MULTI_USER;
GO

-- =============================================
-- 1. BẢNG NGUOI DUNG (NguoiDung)
-- =============================================
CREATE TABLE NguoiDung (
    maNguoiDung INT IDENTITY(1,1) PRIMARY KEY,
    tenDangNhap NVARCHAR(50) NOT NULL UNIQUE,
    email NVARCHAR(100) NOT NULL UNIQUE,
    matKhauHash NVARCHAR(255) NOT NULL,
    hoTen NVARCHAR(100) NOT NULL,
    soDienThoai NVARCHAR(15),
    diaChi NVARCHAR(255),
    ngaySinh DATE,
    gioiTinh NVARCHAR(10) CHECK (gioiTinh IN ('Nam', 'Nu', 'Khac')),
    urlAvatar NVARCHAR(255) DEFAULT 'https://i.pravatar.cc/150?img=5', -- Ảnh đại diện mặc định
    vaiTro NVARCHAR(20) NOT NULL DEFAULT 'CUSTOMER' CHECK (vaiTro IN ('ADMIN', 'CUSTOMER')),
    trangThai NVARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (trangThai IN ('ACTIVE', 'INACTIVE', 'BANNED')),
    thoiGianTao DATETIME2 DEFAULT GETDATE(),
    thoiGianCapNhat DATETIME2 DEFAULT GETDATE(),
    dangNhapCuoi DATETIME2
);

-- =============================================
-- 2. BẢNG DANH MUC TOUR (DanhMuc)
-- =============================================
CREATE TABLE DanhMuc (
    maDanhMuc INT IDENTITY(1,1) PRIMARY KEY,
    tenDanhMuc NVARCHAR(100) NOT NULL UNIQUE,
    moTa NVARCHAR(500),
    urlIcon NVARCHAR(255),
    trangThai BIT NOT NULL DEFAULT 1,
    thoiGianTao DATETIME2 DEFAULT GETDATE()
);

-- =============================================
-- 3. BẢNG DIEM DEN (DiemDen)
-- =============================================
CREATE TABLE DiemDen (
    maDiemDen INT IDENTITY(1,1) PRIMARY KEY,
    tenDiemDen NVARCHAR(100) NOT NULL,
    quocGia NVARCHAR(50) NOT NULL DEFAULT N'Việt Nam',
    thanhPho NVARCHAR(50) NOT NULL,
    moTa NVARCHAR(1000),
    urlHinhAnh NVARCHAR(255),
    viDo DECIMAL(10,8),
    kinhDo DECIMAL(11,8),
    trangThai BIT NOT NULL DEFAULT 1,
    thoiGianTao DATETIME2 DEFAULT GETDATE()
);

-- =============================================
-- 4. BẢNG TOUR (Tour)
-- =============================================
CREATE TABLE Tour (
    maTour INT IDENTITY(1,1) PRIMARY KEY,
    tenTour NVARCHAR(200) NOT NULL,
    maTourCode NVARCHAR(20) NOT NULL UNIQUE,
    maDanhMuc INT NOT NULL,
    maDiemDen INT NOT NULL,
    moTa NVARCHAR(2000),
    thoiGian NVARCHAR(50) NOT NULL,  -- e.g., '1 Ngày', '1 Ngày 1 Đêm'
    kichThuocNhomToiDa INT NOT NULL CHECK (kichThuocNhomToiDa > 0),
    gia DECIMAL(18,2) NOT NULL CHECK (gia >= 0),
    tyLeGiamGia DECIMAL(5,2) DEFAULT 0 CHECK (tyLeGiamGia >= 0 AND tyLeGiamGia <= 100),
    urlHinhAnhChinh NVARCHAR(255),
    thoiGianBatDau TIME,  -- e.g., '09:00'
    thoiGianKetThuc TIME,    -- e.g., '17:30'
    ngonNguHuongDan NVARCHAR(100),  -- e.g., 'Tiếng Anh, Tiếng Việt'
    quyTac NVARCHAR(500),  -- e.g., 'Không được cách xa quá 1km'
    diemDanhGiaTrungBinh DECIMAL(3,1) DEFAULT 0,
    soLuongDanhGia INT DEFAULT 0,
    trangThai NVARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (trangThai IN ('ACTIVE', 'INACTIVE', 'DRAFT')),
    noiBat BIT DEFAULT 0, -- Tour nổi bật
    taoBoi INT NOT NULL,
    thoiGianTao DATETIME2 DEFAULT GETDATE(),
    thoiGianCapNhat DATETIME2 DEFAULT GETDATE(),
    
    CONSTRAINT FK_Tour_DanhMuc FOREIGN KEY (maDanhMuc) REFERENCES DanhMuc(maDanhMuc),
    CONSTRAINT FK_Tour_DiemDen FOREIGN KEY (maDiemDen) REFERENCES DiemDen(maDiemDen),
    CONSTRAINT FK_Tour_TaoBoi FOREIGN KEY (taoBoi) REFERENCES NguoiDung(maNguoiDung)
);

-- =============================================
-- 5. BẢNG HINH ANH TOUR (HinhAnhTour)
-- =============================================
CREATE TABLE HinhAnhTour (
    maHinhAnh INT IDENTITY(1,1) PRIMARY KEY,
    maTour INT NOT NULL,
    urlHinhAnh NVARCHAR(255) NOT NULL,
    tieuDeHinhAnh NVARCHAR(100),
    laChinh BIT DEFAULT 0,
    thuTuHienThi INT DEFAULT 0,
    thoiGianTao DATETIME2 DEFAULT GETDATE(),
    
    CONSTRAINT FK_HinhAnhTour_Tour FOREIGN KEY (maTour) REFERENCES Tour(maTour) ON DELETE CASCADE
);

-- =============================================
-- 6. BẢNG LICH KHOI HANH (LichKhoiHanh)
-- =============================================
CREATE TABLE LichKhoiHanh (
    maLich INT IDENTITY(1,1) PRIMARY KEY,
    maTour INT NOT NULL,
    ngayKhoiHanh DATE NOT NULL,
    ngayTroVe DATE NOT NULL,
    soChoConLai INT NOT NULL CHECK (soChoConLai >= 0),
    soChoDaDat INT DEFAULT 0 CHECK (soChoDaDat >= 0),
    gia DECIMAL(18,2) NOT NULL CHECK (gia >= 0),
    trangThai NVARCHAR(20) NOT NULL DEFAULT 'AVAILABLE' CHECK (trangThai IN ('AVAILABLE', 'FULL', 'CANCELLED', 'COMPLETED')),
    tenHuongDanVien NVARCHAR(100),
    soDienThoaiHuongDanVien NVARCHAR(15),
    diemHen NVARCHAR(255),
    ghiChuDacBiet NVARCHAR(500),
    thoiGianTao DATETIME2 DEFAULT GETDATE(),
    
    CONSTRAINT FK_LichKhoiHanh_Tour FOREIGN KEY (maTour) REFERENCES Tour(maTour),
    CONSTRAINT CHK_LichKhoiHanh_Ngay CHECK (ngayTroVe >= ngayKhoiHanh),
    CONSTRAINT CHK_LichKhoiHanh_Cho CHECK (soChoDaDat <= soChoConLai)
);

-- =============================================
-- 7. BẢNG DAT TOUR (DatTour)
-- =============================================
CREATE TABLE DatTour (
    maDatTour INT IDENTITY(1,1) PRIMARY KEY,
    maDatTourCode NVARCHAR(20) NOT NULL UNIQUE,
    maNguoiDung INT NOT NULL,
    maLich INT NOT NULL,
    soNguoiLon INT NOT NULL DEFAULT 1 CHECK (soNguoiLon > 0),
    soTreEm INT DEFAULT 0 CHECK (soTreEm >= 0),
    tongTien DECIMAL(18,2) NOT NULL CHECK (tongTien >= 0),
    tienGiamGia DECIMAL(18,2) DEFAULT 0 CHECK (tienGiamGia >= 0),
    tienThanhToan DECIMAL(18,2) NOT NULL CHECK (tienThanhToan >= 0),
    trangThaiDatTour NVARCHAR(20) NOT NULL DEFAULT 'PENDING' CHECK (trangThaiDatTour IN ('PENDING', 'CONFIRMED', 'ONGOING', 'BOOKED', 'CANCELLED', 'COMPLETED')),
    trangThaiThanhToan NVARCHAR(20) NOT NULL DEFAULT 'UNPAID' CHECK (trangThaiThanhToan IN ('UNPAID', 'PARTIAL', 'PAID', 'REFUNDED')),
    ghiChuKhachHang NVARCHAR(500),
    ghiChuQuanTri NVARCHAR(500),
    ngayDat DATETIME2 DEFAULT GETDATE(),
    ngayXacNhan DATETIME2,
    ngayHuy DATETIME2,
    lyDoHuy NVARCHAR(500),
    
    CONSTRAINT FK_DatTour_NguoiDung FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung),
    CONSTRAINT FK_DatTour_LichKhoiHanh FOREIGN KEY (maLich) REFERENCES LichKhoiHanh(maLich)
);

-- =============================================
-- 8. BẢNG THANH TOAN (ThanhToan)
-- =============================================
CREATE TABLE ThanhToan (
    maThanhToan INT IDENTITY(1,1) PRIMARY KEY,
    maDatTour INT NOT NULL,
    phuongThucThanhToan NVARCHAR(20) NOT NULL CHECK (phuongThucThanhToan IN ('CASH_ON_DELIVERY', 'BANK_TRANSFER', 'VNPAY', 'MOMO', 'CREDIT_CARD')),
    soTien DECIMAL(18,2) NOT NULL CHECK (soTien > 0),
    maGiaoDich NVARCHAR(100), -- ID giao dịch từ cổng thanh toán
    trangThaiThanhToan NVARCHAR(20) NOT NULL DEFAULT 'PENDING' CHECK (trangThaiThanhToan IN ('PENDING', 'SUCCESS', 'FAILED', 'CANCELLED')),
    ngayThanhToan DATETIME2 DEFAULT GETDATE(),
    ngayXuLy DATETIME2,
    lyDoThatBai NVARCHAR(255),
    ghiChu NVARCHAR(500),
    
    CONSTRAINT FK_ThanhToan_DatTour FOREIGN KEY (maDatTour) REFERENCES DatTour(maDatTour)
);

-- =============================================
-- 9. BẢNG DANH GIA (DanhGia) - THÊM ĐỂ HỖ TRỢ RATING
-- =============================================
CREATE TABLE DanhGia (
    maDanhGia INT IDENTITY(1,1) PRIMARY KEY,
    maTour INT NOT NULL,
    maNguoiDung INT NOT NULL,
    diemSo DECIMAL(3,1) NOT NULL CHECK (diemSo BETWEEN 0 AND 5),
    binhLuan NVARCHAR(1000),
    thoiGianTao DATETIME2 DEFAULT GETDATE(),
    
    CONSTRAINT FK_DanhGia_Tour FOREIGN KEY (maTour) REFERENCES Tour(maTour),
    CONSTRAINT FK_DanhGia_NguoiDung FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung)
);

-- =============================================
-- 11. BẢNG THONG TIN KHACH HANG THAM GIA (ThongTinKhachHangThamGia)
-- =============================================
CREATE TABLE ThongTinKhachHangThamGia (
    maThongTin INT IDENTITY(1,1) PRIMARY KEY,
    maDatTour INT NOT NULL,
    hoTen NVARCHAR(100) NOT NULL,
    ngaySinh DATE,
    soDienThoai NVARCHAR(15),
    gioiTinh NVARCHAR(10) CHECK (gioiTinh IN ('Nam', 'Nu', 'Khac')),
    diaChi NVARCHAR(255),
    ghiChu NVARCHAR(500),
    thoiGianTao DATETIME2 DEFAULT GETDATE(),
    
    CONSTRAINT FK_ThongTinKhachHangThamGia_DatTour FOREIGN KEY (maDatTour) REFERENCES DatTour(maDatTour) ON DELETE CASCADE
);

-- Thêm dữ liệu mẫu cho NguoiDung
INSERT INTO NguoiDung (tenDangNhap, email, matKhauHash, hoTen, soDienThoai, diaChi, ngaySinh, gioiTinh, vaiTro, trangThai, urlAvatar) VALUES
('admin', 'admin@tourism.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXYLFSaZNqsoYgGvZCTrAp/YCFG', N'Quản trị viên', '0901234567', N'123 Nguyễn Huệ, Q1, TP.HCM', '1985-01-15', 'Nam', 'ADMIN', 'ACTIVE', '/images/accounts/hanoi.jpg'),
('customer01', 'nguyenlongnhat@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXYLFSaZNqsoYgGvZCTrAp/YCFG', N'Nguyễn Long Nhật', '0989904735', N'79/4 cách mạng tháng 8, Quận 10, TP.HCM', '2005-11-01', 'Nam', 'CUSTOMER', 'ACTIVE', '/images/accounts/hanoi.jpg'),
('customer02', 'customer02@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXYLFSaZNqsoYgGvZCTrAp/YCFG', N'Lê Văn C', '0904567890', N'321 Cách Mạng Tháng 8, Q10, TP.HCM', '1988-12-25', 'Nam', 'CUSTOMER', 'ACTIVE', '/images/accounts/hanoi.jpg'),
('customer03', 'customer03@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXYLFSaZNqsoYgGvZCTrAp/YCFG', N'Trần Thị B', '0903456789', N'789 Nguyễn Trãi, Q5, TP.HCM', '1995-08-10', 'Nu', 'CUSTOMER', 'ACTIVE', '/images/accounts/hanoi.jpg'),
('customer04', 'customer04@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXYLFSaZNqsoYgGvZCTrAp/YCFG', N'Phạm Văn D', '0905678901', N'654 Võ Văn Tần, Q3, TP.HCM', '1992-03-18', 'Nam', 'CUSTOMER', 'ACTIVE', '/images/accounts/hanoi.jpg'),
('customer05', 'customer05@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXYLFSaZNqsoYgGvZCTrAp/YCFG', N'Hoàng Thị E', '0906789012', N'987 Lê Lợi, Q1, TP.HCM', '1990-06-22', 'Nu', 'CUSTOMER', 'ACTIVE', DEFAULT);

-- Thêm dữ liệu mẫu cho DanhMuc
INSERT INTO DanhMuc (tenDanhMuc, moTa, urlIcon) VALUES
(N'ALL', N'Tất cả các tour du lịch', '/images/tours/all.png'),
(N'Đà Nẵng', N'Tour tại Đà Nẵng', '/images/tours/all.png'),
(N'Nha Trang', N'Tour tại Nha Trang', '/images/tours/all.png'),
(N'Vũng Tàu', N'Tour tại Vũng Tàu', '/images/tours/all.png'),
(N'Quảng Ninh', N'Tour tại Quảng Ninh', '/images/tours/all.png'),
(N'Cao Bằng', N'Tour tại Cao Bằng', '/images/tours/all.png');

-- Thêm dữ liệu mẫu cho DiemDen
INSERT INTO DiemDen (tenDiemDen, thanhPho, moTa, urlHinhAnh, viDo, kinhDo) VALUES
(N'Đà Nẵng', N'Đà Nẵng', N'Thành phố biển với cầu Rồng và Bà Nà Hills', '/images/tours/all.png', 16.0471, 108.2062),
(N'Quảng Ninh', N'Quảng Ninh', N'Vịnh Hạ Long di sản thế giới', '/images/tours/all.png', 20.9101, 107.1839),
(N'Cao Bằng', N'Cao Bằng', N'Thác Bản Giốc và hang động đẹp', '/images/tours/all.png', 22.6667, 106.2500),
(N'Nha Trang', N'Khánh Hòa', N'Bãi biển đẹp, lặn san hô', '/images/tours/all.png', 12.2388, 109.1967),
(N'Vũng Tàu', N'Bà Rịa - Vũng Tàu', N'Bãi biển gần Sài Gòn', '/images/tours/all.png', 10.3460, 107.0843),
(N'Hà Nội', N'Hà Nội', N'Thủ đô với hồ Hoàn Kiếm và phố cổ', '/images/tours/all.png', 21.0285, 105.8542);

-- Thêm dữ liệu mẫu cho Tour
INSERT INTO Tour (tenTour, maTourCode, maDanhMuc, maDiemDen, moTa, thoiGian, kichThuocNhomToiDa, gia, tyLeGiamGia, urlHinhAnhChinh, thoiGianBatDau, thoiGianKetThuc, ngonNguHuongDan, quyTac, diemDanhGiaTrungBinh, soLuongDanhGia, trangThai, noiBat, taoBoi) VALUES
(N'Tour Du Lịch - Tham Quan, Check in tại Quảng Ninh', 'QN001', 5, 2, N'Khám phá Vịnh Hạ Long, check-in các điểm nổi bật', N'1 Ngày', 20, 490000, 0, '/images/tours/all.png', '09:00', '17:30', N'Tiếng Anh, Tiếng Việt', N'Tham quan theo nhóm, không được cách xa quá 1km', 5.0, 12456, 'ACTIVE', 1, 1),
(N'Tour Du Lịch - Tham Quan tại Đà Nẵng City', 'DN001', 2, 1, N'Tham quan cầu Rồng, biển Mỹ Khê', N'1 Ngày 1 Đêm', 25, 2000000, 10, '/images/tours/all.png', '08:00', '22:00', N'Tiếng Việt', N'Giữ vệ sinh môi trường', 4.5, 162, 'ACTIVE', 1, 1),
(N'Tour Du Lịch - Tham Quan, Cứu trợ tại Cao Bằng', 'CB001', 6, 3, N'Khám phá thác Bản Giốc, hoạt động cứu trợ', N'2 Ngày 1 Đêm', 15, 1000000, 5, '/images/tours/all.png', '07:00', '18:00', N'Tiếng Việt', N'Mang theo đồ dùng cá nhân', 5.0, 100, 'ACTIVE', 0, 1),
(N'Tour Du Lịch - Tham Quan tại Nha Trang', 'NT001', 3, 4, N'Tắm biển, lặn ngắm san hô', N'1 Ngày', 30, 2500000, 0, '/images/tours/all.png', '08:00', '17:00', N'Tiếng Việt', N'Không mang đồ ăn bên ngoài', 5.0, 50, 'ACTIVE', 0, 1),
(N'Tour Du Lịch - Tham Quan tại Vũng Tàu', 'VT001', 4, 5, N'Tham quan bãi biển Long Hải', N'1 Ngày', 20, 500000, 0, '/images/tours/all.png', '07:00', '18:00', N'Tiếng Việt', N'Giữ an toàn khi tắm biển', 4.8, 80, 'ACTIVE', 0, 1),
(N'Tour Du Lịch - Tham Quan tại Hà Nội', 'HN001', 1, 6, N'Khám phá hồ Hoàn Kiếm, phố cổ', N'1 Ngày', 25, 2000000, 5, '/images/tours/hanoi.jpg', '09:00', '18:00', N'Tiếng Việt', N'Tuân thủ quy định di tích', 4.5, 200, 'ACTIVE', 1, 1),
(N'Tour Du Lịch - Các Bãi Biển Đà Nẵng', 'DN002', 2, 1, N'Tham quan các bãi biển nổi tiếng ở Đà Nẵng', N'1 Ngày', 30, 2500000, 10, '/images/tours/all.png', '08:00', '17:00', N'Tiếng Việt', N'Bảo vệ môi trường biển', 5.0, 120, 'ACTIVE', 1, 1);

-- Thêm dữ liệu mẫu cho HinhAnhTour
INSERT INTO HinhAnhTour (maTour, urlHinhAnh, tieuDeHinhAnh, laChinh, thuTuHienThi) VALUES
(1, '/images/tours/hanoi.jpg', N'Vịnh Hạ Long', 1, 1),
(1, '/images/tours/quangninh-extra.jpg', N'Check-in Quảng Ninh', 0, 2),
(2, '/images/tours/hanoi.jpg', N'Cầu Rồng Đà Nẵng', 1, 1),
(2, '/images/tours/danang-extra.jpg', N'Biển Mỹ Khê', 0, 2),
(3, '/images/tours/hanoi.jpg', N'Thác Bản Giốc', 1, 1),
(3, '/images/tours/caobang-extra.jpg', N'Hang động Cao Bằng', 0, 2),
(4, '/images/tours/hanoi.jpg', N'Bãi biển Nha Trang', 1, 1),
(4, '/images/tours/nhatrang-extra.jpg', N'Lặn san hô', 0, 2),
(5, '/images/tours/hanoi.jpg', N'Bãi biển Vũng Tàu', 1, 1),
(5, '/images/tours/vungtau-extra.jpg', N'Long Hải', 0, 2),
(6, '/images/tours/hanoi-hoankiem.jpg', N'Hồ Hoàn Kiếm', 1, 1),
(6, '/images/tours/hanoi-phoco.jpg', N'Phố cổ Hà Nội', 0, 2),
(7, '/images/tours/danang-my-an.jpg', N'Bãi biển Mỹ An', 1, 1),
(7, '/images/tours/danang-my-khe.jpg', N'Bãi biển Mỹ Khê', 0, 2);

-- Thêm dữ liệu mẫu cho LichKhoiHanh
INSERT INTO LichKhoiHanh (maTour, ngayKhoiHanh, ngayTroVe, soChoConLai, soChoDaDat, gia, trangThai, tenHuongDanVien, soDienThoaiHuongDanVien, diemHen) VALUES
(1, '2025-09-28', '2025-09-28', 20, 0, 490000, 'AVAILABLE', N'Nguyễn Văn Guide', '0911111111', N'Văn phòng du lịch Quảng Ninh'),
(2, '2025-09-28', '2025-09-29', 25, 5, 1800000, 'AVAILABLE', N'Trần Thị Hướng', '0922222222', N'Sân bay Đà Nẵng'),
(3, '2025-09-28', '2025-09-30', 15, 3, 950000, 'AVAILABLE', N'Lê Văn Nam', '0933333333', N'Ga Cao Bằng'),
(4, '2025-10-01', '2025-10-01', 30, 0, 2500000, 'AVAILABLE', N'Phạm Thị Mai', '0944444444', N'Sân bay Cam Ranh'),
(5, '2025-10-02', '2025-10-02', 20, 2, 500000, 'AVAILABLE', N'Hoàng Văn Dũng', '0955555555', N'Bến xe Vũng Tàu'),
(6, '2025-10-05', '2025-10-05', 25, 0, 2000000, 'AVAILABLE', N'Vũ Thị Lan', '0966666666', N'Ga Hà Nội'),
(7, '2025-10-03', '2025-10-03', 30, 4, 2500000, 'AVAILABLE', N'Ngô Văn Bình', '0977777777', N'Sân bay Đà Nẵng'),
(2, '2025-10-10', '2025-10-11', 25, 0, 2000000, 'AVAILABLE', N'Trần Thị Hướng', '0922222222', N'Sân bay Đà Nẵng');

-- Thêm dữ liệu mẫu cho DatTour
INSERT INTO DatTour (maDatTourCode, maNguoiDung, maLich, soNguoiLon, soTreEm, tongTien, tienGiamGia, tienThanhToan, trangThaiDatTour, trangThaiThanhToan, ghiChuKhachHang) VALUES
('BK000001', 2, 2, 1, 1, 910000, 50000, 860000, 'BOOKED', 'PAID', N'Yêu cầu hướng dẫn viên tiếng Việt'),
('BK000002', 3, 3, 2, 0, 1900000, 0, 1900000, 'ONGOING', 'UNPAID', N'Ghi chú đặc biệt'),
('BK000003', 4, 1, 1, 0, 490000, 0, 490000, 'COMPLETED', 'PAID', N'Tour tuyệt vời'),
('BK000004', 5, 4, 2, 1, 7500000, 500000, 7000000, 'BOOKED', 'PAID', N'Ưu đãi gia đình'),
('BK000005', 2, 7, 1, 0, 2500000, 250000, 2250000, 'CANCELLED', 'REFUNDED', N'Lý do cá nhân'),
('BK000006', 3, 6, 3, 0, 6000000, 300000, 5700000, 'CONFIRMED', 'PARTIAL', N'Đã thanh toán 50%');

-- Thêm dữ liệu mẫu cho ThanhToan
INSERT INTO ThanhToan (maDatTour, phuongThucThanhToan, soTien, maGiaoDich, trangThaiThanhToan, ngayThanhToan, ngayXuLy) VALUES
(1, 'VNPAY', 860000, 'VNP_20251003_001', 'SUCCESS', '2025-10-01 14:30:00', '2025-10-01 14:31:15'),
(3, 'MOMO', 490000, 'MOMO_20251005_002', 'SUCCESS', '2025-10-05 10:00:00', '2025-10-05 10:01:00'),
(4, 'BANK_TRANSFER', 7000000, 'BT_20251006_003', 'SUCCESS', '2025-10-06 15:00:00', '2025-10-06 15:05:00'),
(6, 'CREDIT_CARD', 2850000, 'CC_20251007_004', 'SUCCESS', '2025-10-07 09:00:00', '2025-10-07 09:02:00');

-- Thêm dữ liệu mẫu cho DanhGia
INSERT INTO DanhGia (maTour, maNguoiDung, diemSo, binhLuan, thoiGianTao) VALUES
(1, 2, 5.0, N'Tour tuyệt vời, hướng dẫn viên nhiệt tình', '2025-10-01 10:00:00'),
(2, 3, 4.5, N'Đà Nẵng đẹp, nhưng thời tiết nóng', '2025-09-30 15:00:00'),
(2, 4, 4.0, N'Good tour, nice beaches', '2025-10-02 12:00:00'),
(3, 5, 5.0, N'Thác nước đẹp, hoạt động cứu trợ ý nghĩa', '2025-10-03 14:00:00'),
(4, 2, 5.0, N'Lặn biển thú vị', '2025-10-04 16:00:00'),
(5, 3, 4.8, N'Bãi biển sạch đẹp', '2025-10-05 18:00:00'),
(6, 4, 4.5, N'Phố cổ Hà Nội cổ kính', '2025-10-06 20:00:00'),
(7, 5, 5.0, N'Bãi biển Đà Nẵng tuyệt vời', '2025-10-07 22:00:00');

-- Thêm dữ liệu mẫu cho ThongTinKhachHangThamGia
INSERT INTO ThongTinKhachHangThamGia (maDatTour, hoTen, ngaySinh, soDienThoai, gioiTinh, diaChi, ghiChu) VALUES
(1, N'Nguyễn Văn A', '1990-05-15', '0912345678', 'Nam', N'123 Lê Lợi, Q1, TP.HCM', N'Không dị ứng thực phẩm'),
(1, N'Trần Thị B', '1992-08-20', '0923456789', 'Nu', N'456 Nguyễn Văn Cừ, Q5, TP.HCM', N'Mang theo thuốc cá nhân'),
(2, N'Lê Văn C', '1988-03-10', '0934567890', 'Nam', N'789 Hai Bà Trưng, Q3, TP.HCM', N'Không ăn hải sản'),
(3, N'Phạm Thị D', '1995-11-25', '0945678901', 'Nu', N'321 Trần Phú, Nha Trang', N''),
(4, N'Hoàng Văn E', '1985-07-12', '0956789012', 'Nam', N'654 Lý Thường Kiệt, Đà Nẵng', N''),
(4, N'Nguyễn Thị F', '1987-09-18', '0967890123', 'Nu', N'987 Hùng Vương, Đà Nẵng', N'Có trẻ nhỏ đi cùng');

ALTER TABLE DatTour
ADD thoiGianHuy DATETIME NULL;

-- Script SQL để sửa dữ liệu tour bị lỗi

-- 1. Kiểm tra các tour bị thiếu maDanhMuc
SELECT maTour, tenTour, maTourCode, maDanhMuc, maDiemDen, trangThai
FROM Tour
WHERE maDanhMuc IS NULL;

-- 2. Kiểm tra các tour bị thiếu maDiemDen
SELECT maTour, tenTour, maTourCode, maDanhMuc, maDiemDen, trangThai
FROM Tour
WHERE maDiemDen IS NULL;

-- 3. Kiểm tra các tour không có trạng thái ACTIVE
SELECT maTour, tenTour, maTourCode, maDanhMuc, maDiemDen, trangThai
FROM Tour
WHERE trangThai != 'ACTIVE';

-- 4. Lấy thông tin danh_muc mặc định để gán cho tour thiếu maDanhMuc
SELECT TOP 1 maDanhMuc FROM DanhMuc WHERE trangThai = 1 ORDER BY maDanhMuc;

-- 5. Lấy thông tin diem_den mặc định để gán cho tour thiếu maDiemDen
SELECT TOP 1 maDiemDen FROM DiemDen WHERE trangThai = 1 ORDER BY maDiemDen;

-- 6. Cập nhật tour thiếu maDanhMuc
-- Sử dụng maDanhMuc = 1 (giả định từ kết quả bước 4)
UPDATE Tour
SET maDanhMuc = 1
WHERE maDanhMuc IS NULL;

-- 7. Cập nhật tour thiếu maDiemDen
-- Sử dụng maDiemDen = 1 (giả định từ kết quả bước 5)
UPDATE Tour
SET maDiemDen = 1
WHERE maDiemDen IS NULL;

-- 8. Cập nhật trạng thái tour thành ACTIVE
UPDATE Tour
SET trangThai = 'ACTIVE'
WHERE trangThai != 'ACTIVE' OR trangThai IS NULL;

-- 9. Kiểm tra lại sau khi cập nhật
SELECT maTour, tenTour, maTourCode, maDanhMuc, maDiemDen, trangThai
FROM Tour
WHERE maDanhMuc IS NULL OR maDiemDen IS NULL OR trangThai != 'ACTIVE';

-- 10. Kiểm tra toàn bộ tour có trạng thái ACTIVE
SELECT COUNT(*) AS so_tour_active FROM Tour WHERE trangThai = 'ACTIVE';
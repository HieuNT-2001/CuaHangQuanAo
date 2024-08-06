-- TẠO DATABASE
CREATE DATABASE PRO1041_CuaHangQuanAo
GO

USE PRO1041_CuaHangQuanAo
GO

-- TẠO BẢNG NHÂN VIÊN
CREATE TABLE NhanVien
(
    MaNV INT PRIMARY KEY IDENTITY(1,1),
    TenDangNhap VARCHAR(20) NOT NULL UNIQUE,
    MatKhau VARCHAR(50) NOT NULL,
    HoTen NVARCHAR(50) NOT NULL,
    NamSinh INT NOT NULL CHECK(NamSinh > 0),
    SDT VARCHAR(10) NOT NULL,
    CCCD VARCHAR(12) NOT NULL,
    VaiTro BIT NOT NULL
)
GO

-- TẠO BẢNG NHÀ CUNG CẤP
CREATE TABLE NhaCungCap
(
    MaNCC INT PRIMARY KEY IDENTITY(1,1),
    TenNCC NVARCHAR(50) NOT NULL,
    SDT VARCHAR(10) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    DiaChi NVARCHAR(255) NOT NULL
)
GO

-- TẠO BẢNG DANH MỤC SẢN PHẨM
CREATE TABLE DanhMuc
(
    MaDM INT PRIMARY KEY IDENTITY(1,1),
    TenDM NVARCHAR(30) NOT NULL,
)
GO

-- TẠO BẢNG MÀU SẮC
CREATE TABLE MauSac
(
    MaMS INT PRIMARY KEY IDENTITY(1,1),
    MauSac NVARCHAR(30) NOT NULL,
)
GO

-- TẠO BẢNG KÍCH THƯỚC
CREATE TABLE KichThuoc
(
    MaKT INT PRIMARY KEY IDENTITY(1,1),
    KichThuoc NVARCHAR(30) NOT NULL,
)
GO

-- TẠO BẢNG KHUYẾN MẠI
CREATE TABLE KhuyenMai
(
    MaKM INT PRIMARY KEY IDENTITY(1,1),
    TenKM VARCHAR(30) NOT NULL UNIQUE,
    NgayBD DATE NOT NULL,
    NgayKT DATE NOT NULL,
    GiamGia FLOAT NOT NULL CHECK (GiamGia BETWEEN 0 and 1)
)
GO

-- TẠO BẢNG SẢN PHẨM
CREATE TABLE SanPham
(
    MaSP INT PRIMARY KEY IDENTITY(1,1),
    TenSP NVARCHAR(50) NOT NULL,
    DonGia FLOAT NOT NULL CHECK(DonGia > 0),
    SoLuong INT NOT NULL CHECK(SoLuong >= 0),
    MaNCC INT NOT NULL,
    MaDM INT NOT NULL,
    TrangThai BIT NOT NULL,
    FOREIGN KEY (MaNCC) REFERENCES NhaCungCap (MaNCC),
    FOREIGN KEY (MaDM) REFERENCES DanhMuc (MaDM)
)
GO

-- TẠO BẢNG CHI TIẾT SẢN PHẨM
CREATE TABLE ChiTietSP
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    MaSP INT NOT NULL,
    MaMS INT NOT NULL,
    MaKT INT NOT NULL,
    Hinh NVARCHAR(255) NOT NULL,
    FOREIGN KEY (MaSP) REFERENCES SanPham (MaSP),
    FOREIGN KEY (MaMS) REFERENCES MauSac (MaMS),
    FOREIGN KEY (MaKT) REFERENCES KichThuoc (MaKT),
)

-- TẠO BẢNG HOÁ ĐƠN
CREATE TABLE HoaDon
(
    MaHD INT PRIMARY KEY IDENTITY(1,1),
    MaNV INT NOT NULL,
    TenKH NVARCHAR(50),
    SDT VARCHAR(10),
    DiaChi NVARCHAR(255),
    MaGiamGia VARCHAR(30),
    KenhBanHang BIT NOT NULL,
    HT_ThanhToan BIT NOT NULL,
    ThanhTien FLOAT NOT NULL CHECK (ThanhTien >= 0),
    NgayTao DATE NOT NULL,
    TrangThai INT NOT NULL,
    LyDo NVARCHAR(255),
    FOREIGN KEY (MaNV) REFERENCES NhanVien (MaNV),
)
GO

-- TẠO BẢNG HÓA ĐƠN CHI TIẾT
CREATE TABLE HoaDonChiTiet
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    MaHD INT NOT NULL,
    MaSP INT NOT NULL,
    SoLuong INT NOT NULL CHECK(SoLuong > 0),
    GiaBan FLOAT NOT NULL CHECK (GiaBan > 0),
    FOREIGN KEY (MaHD) REFERENCES HoaDon (MaHD),
    FOREIGN KEY (MaSP) REFERENCES SanPham (MaSP)
)
GO

-- TẠO SP
-- SP LẤY DOANH THU NGÀY HÔM NAY
CREATE OR ALTER PROCEDURE get_doanhThu_today
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        SUM(ThanhTien) AS DoanhThu
    FROM HoaDon
    WHERE NgayTao = CAST(GETDATE() AS DATE)
        AND TrangThai = 1;
END
GO

-- SP LẤY DOANH THU THEO NGÀY
CREATE OR ALTER PROCEDURE get_doanhThu_byDate
    @start DATE,
    @end DATE
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        SUM(ThanhTien) AS DoanhThu
    FROM HoaDon
    WHERE NgayTao BETWEEN @start AND @end
        AND TrangThai = 1;
END
GO

-- SP LẤY DOANH THU THEO THÁNG HIỆN TẠI
CREATE OR ALTER PROC get_doanhThu_thisMonth
AS
BEGIN
    SET NOCOUNT ON

    SELECT
        SUM(ThanhTien) AS DoanhThu
    FROM HoaDon
    WHERE MONTH(NgayTao) = MONTH(GETDATE())
        AND YEAR(NgayTao) = YEAR(GETDATE())
        AND TrangThai = 1;
END
GO

-- SP LẤY DOANH THEO NĂM HIỆN TẠI
CREATE OR ALTER PROC get_doanhThu_thisYear
AS
BEGIN
    SET NOCOUNT ON

    SELECT
        SUM(ThanhTien) AS DoanhThu
    FROM HoaDon
    WHERE YEAR(NgayTao) = YEAR(GETDATE())
        AND TrangThai = 1;
END
GO

-- SP LẤY CHI TIẾT DOANH THU
CREATE OR ALTER PROCEDURE get_doanhThu_detail
    @year INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        MONTH(hd.NgayTao) AS Thang,
        SUM(hdct.SoLuong) AS SoLuongBan,
        SUM(SoLuong * GiaBan * (1 - ISNULL(km.GiamGia, 0))) AS DoanhThu
    FROM HoaDon hd
        JOIN HoaDonChiTiet hdct ON hd.MaHD = hdct.MaHD
        LEFT JOIN KhuyenMai km ON hd.MaGiamGia = km.TenKM
    WHERE YEAR(hd.NgayTao) = @year
        AND TrangThai = 1
    GROUP BY MONTH(hd.NgayTao)
    ORDER BY MONTH(hd.NgayTao);
END
GO

-- SP ĐẾM HÓA ĐƠN NGÀY HÔM NAY
CREATE OR ALTER PROCEDURE count_hoaDon_today
    @status INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT COUNT(*) AS SoHoaDon
    FROM HoaDon
    WHERE @status = 4 OR TrangThai = @status
        AND NgayTao = CAST(GETDATE() AS DATE)
END
GO

-- SP ĐẾM HÓA ĐƠN THEO NGÀY
CREATE OR ALTER PROCEDURE count_hoaDon_byDate
    @status INT,
    @start DATE,
    @end DATE
AS
BEGIN
    SET NOCOUNT ON;

    SELECT COUNT(*) AS SoHoaDon
    FROM HoaDon
    WHERE @status = 4 OR TrangThai = @status
        AND NgayTao BETWEEN @start AND @end;
END
GO

-- SP ĐẾM HÓA ĐƠN THEO THÁNG HIỆN TẠI
CREATE OR ALTER PROCEDURE count_hoaDon_thisMonth
    @status INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT COUNT(*) AS SoHoaDon
    FROM HoaDon
    WHERE @status = 4 OR TrangThai = @status
        AND MONTH(NgayTao) = MONTH(GETDATE())
        AND YEAR(NgayTao) = YEAR(GETDATE())
END
GO

-- SP ĐỀM HÓA ĐƠN THEO NĂM HIỆN TẠI
CREATE OR ALTER PROCEDURE count_hoaDon_thisYear
    @status INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT COUNT(*) AS SoHoaDon
    FROM HoaDon
    WHERE @status = 4 OR TrangThai = @status
        AND YEAR(NgayTao) = YEAR(GETDATE())
END
GO

-- SP LẤY TOP 10 SẢN PHẨM BÁN CHẠY TRONG THÁNG
CREATE OR ALTER PROCEDURE get_top10_sanPham
    @Thang INT,
    @Nam INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT TOP 10
        'SP' + FORMAT(sp.MaSP, '0000') AS MaSP,
        sp.TenSP,
        ncc.TenNCC,
        SUM(hdct.SoLuong) AS SoLuongBan
    FROM NhaCungCap ncc
        JOIN SanPham sp ON ncc.MaNCC = sp.MaNCC
        JOIN HoaDonChiTiet hdct ON sp.MaSP = hdct.MaSP
        JOIN HoaDon hd ON hdct.MaHD = hd.MaHD
    WHERE MONTH(hd.NgayTao) = @Thang
        AND YEAR(hd.NgayTao) = @Nam
    GROUP BY sp.MaSP, sp.TenSP, ncc.TenNCC
    ORDER BY SoLuongBan DESC;
END
GO

-- -- TRIGGER INSERT HÓA ĐƠN CHI TIẾT
-- CREATE OR ALTER TRIGGER insert_HoaDonChiTiet ON HoaDonChiTiet
-- AFTER INSERT
-- AS
-- BEGIN
--     IF EXISTS (SELECT *
--     FROM inserted)
--     BEGIN
--         UPDATE SanPham
--             SET SoLuong = sp.SoLuong - i.SoLuong
--         FROM SanPham sp
--             JOIN inserted i ON sp.MaSP = i.MaSP
--     END
-- END
-- GO

-- -- TRIGGER UPDATE HÓA ĐƠN CHI TIẾT
-- CREATE OR ALTER TRIGGER update_HoaDonChiTiet ON HoaDonChiTiet
-- AFTER UPDATE
-- AS
-- BEGIN
--     IF EXISTS (SELECT *
--     FROM inserted)
--     BEGIN
--         UPDATE SanPham
--             SET SoLuong = sp.SoLuong - (i.SoLuong - ISNULL(d.SoLuong, 0))
--         FROM SanPham sp
--             JOIN inserted i ON sp.MaSP = i.MaSP
--             LEFT JOIN deleted d ON sp.MaSP = d.MaSP
--     END
-- END
-- GO

-- -- TRIGGER DELETE HÓA ĐƠN CHI TIẾT
-- CREATE OR ALTER TRIGGER delete_hoaDonChiTiet ON HoaDonChiTiet
-- AFTER DELETE
-- AS
-- BEGIN
--     IF EXISTS (SELECT *
--     FROM deleted)
--     BEGIN
--         UPDATE SanPham
--             SET SoLuong = sp.SoLuong + d.SoLuong
--         FROM SanPham sp
--             JOIN deleted d ON sp.MaSP = d.MaSP
--     END
-- END
-- GO

-- TRIGGER THANH TOÁN HÓA ĐƠN
CREATE OR ALTER TRIGGER cancel_HoaDon ON HoaDon
AFTER UPDATE
AS
BEGIN
    IF EXISTS (SELECT *
    FROM inserted
    WHERE TrangThai = 1)
    BEGIN
        UPDATE SanPham
            SET SoLuong = sp.SoLuong - hdct.SoLuong
        FROM SanPham sp
            JOIN HoaDonChiTiet hdct ON sp.MaSP = hdct.MaSP
            JOIN inserted i ON hdct.MaHD = i.MaHD
        WHERE i.TrangThai = 1
    END
END
GO

-- NHẬP DỮ LIỆU
-- NHẬP DỮ LIỆU NHÂN VIÊN
INSERT INTO NhanVien
    (TenDangNhap, MatKhau, HoTen, NamSinh, SDT, CCCD, VaiTro)
VALUES
    ('NamTP', '123', N'Trần Thành Nam', 1994, '0902123456', '045212345678', 1),
    ('ThuNV', '123', N'Bùi Thị Thu', 2000, '0902654321', '045287654321', 0)
GO

-- NHẬP DỮ LIỆU NHÀ CUNG CẤP
INSERT INTO NhaCungCap
    (TenNCC, SDT, Email, DiaChi)
VALUES
    (N'Công ty CP dệt may', '0625123456', '', N'Hải Phòng'),
    (N'Công ty TNHH thời trang', '0625654321', '', N'Hải Phòng'),
    (N'Công ty TNHH Fashion VietNam', '0625225643', '', N'Hải Phòng')
GO

-- NHẬP DỮ LIỆU DANH MỤC
INSERT INTO DanhMuc
    (TenDM)
VALUES
    (N'Áo sơ mi'),
    (N'Quần công sở')
GO

-- NHẬP DỮ LIỆU MÀU SẮC
INSERT INTO MauSac
    (MauSac)
VALUES
    (N'Trắng'),
    (N'Đen')
GO

-- NHẬP DỮ LIỆU KÍCH THƯỚC
INSERT INTO KichThuoc
    (KichThuoc)
VALUES
    ('S'),
    ('M'),
    ('L')
GO

-- NHẬP DỮ LIỆU KHUYẾN MẠI
INSERT INTO KhuyenMai
    (TenKM, NgayBD, NgayKT, GiamGia)
VALUES
    ('summer2024', '2024-01-06', '2024-07-30', 0.1)
GO

-- NHẬP DỮ LIỆU SẢN PHẨM
INSERT INTO SanPham
    (TenSP, DonGia, SoLuong, MaNCC, MaDM, TrangThai)
VALUES
    (N'Áo sơ mi trắng', 120000, 100, 1, 1, 1),
    (N'Quần công sở đen', 150000, 100, 1, 2, 1)
GO

-- NHẬP DỮ LIỆU CHI TIẾT SẢN PHẨM
INSERT INTO ChiTietSP
    (MaSP, MaMS, MaKT, Hinh)
VALUES
    (1, 1, 1, 'somitrang.jpg'),
    (1, 1, 2, 'somitrang.jpg'),
    (1, 1, 3, 'somitrang.jpg'),
    (2, 2, 3, 'quancongsoden.jpg')
GO

-- NHẬP DỮ LIỆU HÓA ĐƠN
INSERT INTO HoaDon
    (MaNV, TenKH, SDT, DiaChi, MaGiamGia, KenhBanHang, HT_ThanhToan, ThanhTien, NgayTao, TrangThai, LyDo)
VALUES
    (2, '', '', '', '', 0, 0    , 150000, '2024-06-30', 1, ''),
    (2, '', '', '', 'summer2024', 0, 0, 891000, '2024-06-30', 1, '')
GO

-- NHẬP DỮ LIỆU HÓA ĐƠN CHI TIẾT
INSERT INTO HoaDonChiTiet
    (MaHD, MaSP, SoLuong, GiaBan)
VALUES
    (1, 1, 1, 150000),
    (2, 1, 3, 150000),
    (2, 2, 3, 180000)
GO

EXEC get_doanhThu_today

EXEC get_doanhThu_byDate '2024-06-01', '2024-07-15'

EXEC get_doanhThu_thisMonth

EXEC get_doanhThu_thisYear

EXEC get_doanhThu_detail 2024

EXEC count_hoaDon_today 1

EXEC count_hoaDon_byDate 1, '2024-06-01', '2024-07-15'

EXEC count_hoaDon_thisMonth 1

EXEC count_hoaDon_thisYear 1

EXEC get_top10_sanPham 6, 2024

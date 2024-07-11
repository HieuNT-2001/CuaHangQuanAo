package entity;

import java.util.Date;

public class HoaDon {

    private int maHD;
    private int maNV;
    private String tenKH;
    private String sdt;
    private String diaChi;
    private String maGiamGia;
    private boolean kenhBanHang;
    private boolean ht_thanhToan;
    private double thanhTien;
    private Date ngayTao;
    private boolean trangThai;
    private String lyDo;

    public HoaDon() {
    }

    public HoaDon(int maHD, int maNV, String tenKH, String sdt, String diaChi, String maGiamGia, boolean kenhBanHang, boolean ht_thanhToan, double thanhTien, Date ngayTao, boolean trangThai, String lyDo) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.maGiamGia = maGiamGia;
        this.kenhBanHang = kenhBanHang;
        this.ht_thanhToan = ht_thanhToan;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.lyDo = lyDo;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public boolean isKenhBanHang() {
        return kenhBanHang;
    }

    public void setKenhBanHang(boolean kenhBanHang) {
        this.kenhBanHang = kenhBanHang;
    }

    public boolean isHt_thanhToan() {
        return ht_thanhToan;
    }

    public void setHt_thanhToan(boolean ht_thanhToan) {
        this.ht_thanhToan = ht_thanhToan;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

}

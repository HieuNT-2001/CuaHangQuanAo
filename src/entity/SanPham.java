package entity;

public class SanPham {

    private int maSP;
    private String tenSP;
    private double donGia;
    private int soLuong;
    private int maNCC;
    private int maDM;
    private int maMS;
    private int maKT;
    private boolean trangThai;

    public SanPham() {
    }

    public SanPham(int maSP, String tenSP, double donGia, int soLuong, int maNCC, int maDM, int maMS, int maKT, boolean trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.maNCC = maNCC;
        this.maDM = maDM;
        this.maMS = maMS;
        this.maKT = maKT;
        this.trangThai = trangThai;
    }

    

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaMS() {
        return maMS;
    }

    public void setMaMS(int maMS) {
        this.maMS = maMS;
    }

    public int getMaKT() {
        return maKT;
    }

    public void setMaKT(int maKT) {
        this.maKT = maKT;
    }
    
    

}

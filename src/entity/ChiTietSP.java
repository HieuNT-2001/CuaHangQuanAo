package entity;

public class ChiTietSP {

    private int id;
    private int maSP;
    private int maMS;
    private int maKT;
    private String hinh;

    public ChiTietSP() {
    }

    public ChiTietSP(int id, int maSP, int maMS, int maKT, String hinh) {
        this.id = id;
        this.maSP = maSP;
        this.maMS = maMS;
        this.maKT = maKT;
        this.hinh = hinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
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

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

}

package entity;

public class KichThuoc {

    private int maKT;
    private String kichThuoc;

    public KichThuoc() {
    }

    public KichThuoc(int maKT, String kichThuoc) {
        this.maKT = maKT;
        this.kichThuoc = kichThuoc;
    }

    public int getMaKT() {
        return maKT;
    }

    public void setMaKT(int maKT) {
        this.maKT = maKT;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }
    
    public String toString() {
        return this.getKichThuoc();
    }

}

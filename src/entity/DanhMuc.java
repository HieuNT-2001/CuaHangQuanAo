package entity;

public class DanhMuc {

    private int maDM;
    private String danhMuc;

    public DanhMuc() {
        
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public DanhMuc(int maDM, String danhMuc) {
        this.maDM = maDM;
        this.danhMuc = danhMuc;
    }
    
    public String toString() {
        return this.getDanhMuc();
    }

}

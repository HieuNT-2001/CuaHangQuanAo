/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.KhuyenMai;
import utils.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author HP
 */
public class KhuyenMaiDAO {
    public void insert(KhuyenMai km){
        String sql="INSERT INTO KhuyenMai (MaKM, TenKM, NgayBD, NgayKT, GiamGia)\n" +
        "VALUES (?, ?, ?, ?)";
        Xjdbc.update(sql, km.getMaKM(),km.getTenKM(),km.getNgayBD(),km.getNgayKT(),km.getGiamGia());
    }
    public void update(KhuyenMai km){
        String sql="UPDATE KhuyenMai SET TenKM=?, NgayBD=?, NgayKT=?, GiamGia=?\n" +
        "WHERE MaKM=?";
        Xjdbc.update(sql, km.getTenKM(),km.getNgayBD(),km.getNgayKT(),km.getGiamGia(),km.getMaKM());
    }
    public void delete(String maKM){
        String sql="DELETE FROM KhuyenMai WHERE MaKM=?";
        Xjdbc.update(sql, maKM);
    }
    public List<KhuyenMai> select(){
        String sql="SELECT * FROM KhuyenMai";
        return select(sql);
    }
    public KhuyenMai findByID(String maNV){
        String sql="SELECT * FROM KhuyenMai WHERE MaNV=?";
        List<KhuyenMai> lkm=select(sql, maNV);
        return lkm.size()>0 ? lkm.get(0) : null;
    }
    private List<KhuyenMai> select(String sql, Object...args){
        List<KhuyenMai> lkm=new ArrayList<>();
        try{
            ResultSet rs = null;
            try{
                rs=Xjdbc.query(sql, args);
                while(rs.next()){
                    KhuyenMai km=readFromResultSet(rs);
                    lkm.add(km);
                }
            }
            finally{
                rs.getStatement();
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lkm;
    }
    private KhuyenMai readFromResultSet(ResultSet rs) throws SQLException{
        KhuyenMai km=new KhuyenMai();
        km.setMaKM(rs.getString("MaKM"));
        km.setTenKM(rs.getString("TenKM"));
        km.setNgayBD(rs.getDate("NgayBD"));
        km.setNgayKT(rs.getDate("NgayKT"));
        km.setGiamGia(rs.getDouble("GiamGia"));
        return km;
    }
}

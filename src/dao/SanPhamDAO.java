/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.SanPham;
import utils.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author HP
 */
public class SanPhamDAO {
    public void insert(SanPham sp){
        String sql="INSERT INTO SanPham (TenSP, DonGia, SoLuong, MaNCC, MaDM, MaMS, MaKT, TrangThai)\n" +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Xjdbc.executeUpdate(sql, sp.getTenSP(),sp.getDonGia(),sp.getSoLuong(),sp.getMaNCC(),
                sp.getMaDM(),sp.getMaMS(),sp.getMaKT(),sp.isTrangThai());
    }
    public void update(SanPham sp){
        String sql="UPDATE SanPham SET TenSP=?, DonGia=?, SoLuong=?, MaNCC=?, MaDM=?, MaMS=?, MaKT=?, TrangThai=?\n" +
        "WHERE MaSP=?";
        Xjdbc.executeUpdate(sql, sp.getTenSP(),sp.getDonGia(),sp.getSoLuong(),sp.getMaNCC(),
                sp.getMaDM(),sp.getMaMS(),sp.getMaKT(),sp.isTrangThai(),sp.getMaSP());
    }
    public void delete(String maSP){
        String sql="DELETE FROM SanPham WHERE MaSP=?";
        Xjdbc.executeUpdate(sql, maSP);
    }
    public List<SanPham> select(){
        String sql="SELECT * FROM SanPham";
        return select(sql);
    }
    public SanPham findByID(String maNV){
        String sql="SELECT * FROM SanPham WHERE MaNV=?";
        List<SanPham> lsp=select(sql, maNV);
        return lsp.size()>0 ? lsp.get(0) : null;
    }
    private List<SanPham> select(String sql, Object...args){
        List<SanPham> lsp=new ArrayList<>();
        try{
            ResultSet rs = null;
            try{
                rs=Xjdbc.executeQuery(sql, args);
                while(rs.next()){
                    SanPham sp=readFromResultSet(rs);
                    lsp.add(sp);
                }
            }
            finally{
                rs.getStatement();
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lsp;
    }
    private SanPham readFromResultSet(ResultSet rs) throws SQLException{
        SanPham sp=new SanPham();
        sp.setMaSP(rs.getInt("MaSP"));
        sp.setTenSP(rs.getString("TenSP"));
        sp.setDonGia(rs.getDouble("DonGia"));
        sp.setSoLuong(rs.getInt("SoLuong"));
        sp.setMaNCC(rs.getInt("MaNCC"));
        sp.setMaDM(rs.getInt("MaDM"));
        sp.setMaMS(rs.getInt("MaMS"));
        sp.setMaKT(rs.getInt("MaKT"));
        sp.setTrangThai(rs.getBoolean("TrangThai"));
        return sp;
    }
}

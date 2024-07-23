/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.ChiTietSP;
import utils.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class ChiTietSPDAO {
public void insert(ChiTietSP CTSP){
        String sql="INSERT INTO ChiTietSanPham (MaSP, MaMS, MaKT, Hinh)\n" +
        "VALUES (?, ?, ?, ?)";
        Xjdbc.update(sql, CTSP.getMaSP(),CTSP.getMaMS(),CTSP.getMaKT(),CTSP.getHinh());
    }
    public void update(ChiTietSP CTSP){
        String sql="UPDATE ChiTietSanPham SET MaSP=?, MaMS=?, MaKT=?, Hinh=?\n" +
        "WHERE MaKM=?";
        Xjdbc.update(sql, CTSP.getMaSP(),CTSP.getMaMS(),CTSP.getMaKT(),CTSP.getHinh());
    }
        public void delete(String MaSP){
        String sql="DELETE FROM ChiTietSanPham WHERE MaSP=?";
        Xjdbc.update(sql, MaSP);
    }
        public List<ChiTietSP> select(){
        String sql="SELECT * FROM ChiTietSanPham";
        return select(sql);
    }
     public ChiTietSP findByID(String MaSP){
        String sql="SELECT * FROM ChiTietSanPham WHERE MaNV=?";
        List<ChiTietSP> lCTSP=select(sql, MaSP);
        return lCTSP.size()>0 ? lCTSP.get(0) : null;
    }  
 private List<ChiTietSP> select(String sql, Object...args){
        List<ChiTietSP> lCTSP=new ArrayList<>();
        try{
            ResultSet rs = null;
            try{
                rs=Xjdbc.query(sql, args);
                while(rs.next()){
                    ChiTietSP km=readFromResultSet(rs);
                    lCTSP.add(km);
                }
            }
            finally{
                rs.getStatement();
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lCTSP;
    }  
 private ChiTietSP readFromResultSet(ResultSet rs) throws SQLException{
        ChiTietSP lCTSP=new ChiTietSP();
        lCTSP.setMaKT(rs.getInt("MaKT"));
        lCTSP.setMaMS(rs.getInt("MaMS"));
        lCTSP.setMaKT(rs.getInt("MaKT"));
        lCTSP.setHinh(rs.getString("Hinh"));
        return lCTSP;
    }
}

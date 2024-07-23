/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.HoaDonChiTiet;
import utils.Xjdbc;
import utils.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietDAO {
    public void insert(HoaDonChiTiet HDCT){
        String sql = "Insert into HoaDonChiTiet (, MaHD, MaSP, SoLuong, GiaBan)\n" + "values (?,?,?,?)";
        Xjdbc.update(sql, HDCT.getMaHD(),HDCT.getMaSP(),HDCT.getSoLuong(),HDCT.getGiaBan());
    }
    public void update(HoaDonChiTiet HDCT){
        String sql = "Update HoaDonChiTiet set  MaHD=?, MaSP = ?, SoLuong =?,GiaBan=?\n" + "where MaHD =?";
        Xjdbc.update(sql,HDCT.getMaHD(),HDCT.getMaSP(),HDCT.getSoLuong(),HDCT.getGiaBan() );
    }
    public void delete (String MaHD){
        String sql="DELETE FROM HoaDonChiTiet WHERE MaHD=?";
        Xjdbc.update(sql, MaHD); 
    }
     public List<HoaDonChiTiet> select(){
        String sql ="SELECT * FROM HoaDonChiTiet";
        return select(sql);
    }
     public HoaDonChiTiet findByID(String MaHD){
        String sql="SELECT * FROM KhuyenMai WHERE MaNV=?";
        List<HoaDonChiTiet> lHDCT=select(sql,MaHD );
        return lHDCT.size()>0 ? lHDCT.get(0) : null;         
     }
      private List<HoaDonChiTiet> select(String sql, Object...args){
        List<HoaDonChiTiet> lHDCT=new ArrayList<>();
        try{
            ResultSet rs = null;
            try{
                rs=Xjdbc.query(sql, args);
                while(rs.next()){
                    HoaDonChiTiet HDCT= readFromResultSet(rs);
                    lHDCT.add(HDCT);
                }
            }
            finally{
                rs.getStatement();
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lHDCT;
}
      private HoaDonChiTiet readFromResultSet(ResultSet rs) throws SQLException{
        HoaDonChiTiet HDCT=new HoaDonChiTiet();
        HDCT.setMaHD(rs.getInt("MaHD"));
        HDCT.setMaSP(rs.getInt("MaSP"));
        HDCT.setSoLuong(rs.getInt("SoLuong"));
        HDCT.setGiaBan(rs.getFloat("GiaBan"));
        return HDCT;
    }
}

   

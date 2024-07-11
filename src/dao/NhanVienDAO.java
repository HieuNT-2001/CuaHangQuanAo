/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.NhanVien;
import utils.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author HP
 */
public class NhanVienDAO {
    public void insert(NhanVien nv){
        String sql="INSERT INTO NhanVien (TenDangNhap, MatKhau, HoTen, NamSinh, SDT, CCCD, VaiTro)\n" +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Xjdbc.executeUpdate(sql, nv.getTenDangNhap(),nv.getMatKhau(),nv.getHoTen(),
                nv.getNamSinh(),nv.getSdt(),nv.getCccd(),nv.isVaiTro());
    }
    public void update(NhanVien nv){
        String sql="UPDATE NhanVien SET TenDangNhap=?, MatKhau=?, HoTen=?, NamSinh=?, SDT=?, CCCD=?, VaiTro=?\n" +
        "WHERE MaNV=?";
        Xjdbc.executeUpdate(sql, nv.getTenDangNhap(),nv.getMatKhau(),nv.getHoTen(),
                nv.getNamSinh(),nv.getSdt(),nv.getCccd(),nv.isVaiTro(),nv.getMaNV());
    }
    public void delete(String maNV){
        String sql="DELETE FROM NhanVien WHERE MaNV=?";
        Xjdbc.executeUpdate(sql, maNV);
    }
    public List<NhanVien> select(){
        String sql="SELECT * FROM NhanVien";
        return select(sql);
    }
    public NhanVien findByID(String maNV){
        String sql="SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> lnv=select(sql, maNV);
        return lnv.size()>0 ? lnv.get(0) : null;
    }
    private List<NhanVien> select(String sql, Object...args){
        List<NhanVien> lnv=new ArrayList<>();
        try{
            ResultSet rs = null;
            try{
                rs=Xjdbc.executeQuery(sql, args);
                while(rs.next()){
                    NhanVien nv=readFromResultSet(rs);
                    lnv.add(nv);
                }
            }
            finally{
                rs.getStatement();
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lnv;
    }
    private NhanVien readFromResultSet(ResultSet rs) throws SQLException{
        NhanVien nv=new NhanVien();
        nv.setMaNV(rs.getInt("MaNV"));
        nv.setTenDangNhap(rs.getString("TenDangNhap"));
        nv.setMatKhau(rs.getString("MatKhau"));
        nv.setHoTen(rs.getString("HoTen"));
        nv.setNamSinh(rs.getInt("NamSinh"));
        nv.setSdt(rs.getString("SDT"));
        nv.setCccd(rs.getString("CCCD"));
        nv.setVaiTro(rs.getBoolean("VaiTro"));
        return nv;
    }
}

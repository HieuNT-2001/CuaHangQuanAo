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

    public void insert(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (TenDangNhap, MatKhau, HoTen, NamSinh, SDT, CCCD, VaiTro)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Xjdbc.update(sql, nv.getTenDangNhap(), nv.getMatKhau(), nv.getHoTen(),
                nv.getNamSinh(), nv.getSdt(), nv.getCccd(), nv.isVaiTro());
    }

    public void update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET TenDangNhap=?, MatKhau=?, HoTen=?, NamSinh=?, SDT=?, CCCD=?, VaiTro=?\n"
                + "WHERE MaNV=?";
        Xjdbc.update(sql, nv.getTenDangNhap(), nv.getMatKhau(), nv.getHoTen(),
                nv.getNamSinh(), nv.getSdt(), nv.getCccd(), nv.isVaiTro(), nv.getMaNV());
    }

    public void delete(int maNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        Xjdbc.update(sql, maNV);
    }

    public List<NhanVien> select() {
        String sql = "SELECT * FROM NhanVien";
        return selectBySql(sql);
    }

    public NhanVien selectById(int maNV) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> lnv = selectBySql(sql, maNV);
        return lnv.size() > 0 ? lnv.get(0) : null;
    }

    public NhanVien selectByUserName(String tenDangNhap) {
        String sql = "SELECT * FROM NhanVien WHERE TenDangNhap = ?";
        List<NhanVien> lnv = selectBySql(sql,tenDangNhap);
        return lnv.size() > 0 ? lnv.get(0) : null;
    }

    private List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> lnv = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Xjdbc.query(sql, args);
                while (rs.next()) {
                    NhanVien nv = readFromResultSet(rs);
                    lnv.add(nv);
                }
            } finally {
                rs.getStatement();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lnv;
    }

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien nv = new NhanVien();
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

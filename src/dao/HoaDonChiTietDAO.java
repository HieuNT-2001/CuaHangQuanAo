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

    public void insert(HoaDonChiTiet HDCT) {

        String sql = "Insert into HoaDonChiTiet (MaHD, MaSP, SoLuong, GiaBan)\n" + "values (?,?,?,?)";
        Xjdbc.update(sql, HDCT.getMaHD(), HDCT.getMaSP(), HDCT.getSoLuong(), HDCT.getGiaBan());
    }

    public void update(HoaDonChiTiet HDCT) {
        String sql = "Update HoaDonChiTiet set MaHD=?, MaSP = ?, SoLuong =?,GiaBan=?\n" + "where ID =?";
        Xjdbc.update(sql, HDCT.getMaHD(), HDCT.getMaSP(), HDCT.getSoLuong(), HDCT.getGiaBan(), HDCT.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM HoaDonChiTiet WHERE ID=?";
        Xjdbc.update(sql, id);
    }

    public void deleteByMaHD_MaSP(int maHD, int maSP) {
        String sql = "DELETE FROM HoaDonChiTiet WHERE MaHD = ? AND MaSP = ?";
        Xjdbc.update(sql, maHD, maSP);
    }

    public List<HoaDonChiTiet> select() {
        String sql = "SELECT * FROM HoaDonChiTiet";
        return selectBySql(sql);
    }

    public HoaDonChiTiet selectById(int id) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE ID=?";
        List<HoaDonChiTiet> lHDCT = selectBySql(sql, id);
        return lHDCT.size() > 0 ? lHDCT.get(0) : null;
    }

    public List<HoaDonChiTiet> selectByMaHD(int maHD) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHD = ?";
        return selectBySql(sql, maHD);
    }
    
    public HoaDonChiTiet selectByMaHD_MaSP(int maHD, int maSP) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHD = ? AND MaSP = ?";
        List<HoaDonChiTiet> lHDCT = selectBySql(sql, maHD, maSP);
        return lHDCT.size() > 0 ? lHDCT.get(0) : null;

    }

    private List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> lHDCT = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Xjdbc.query(sql, args);
                while (rs.next()) {
                    HoaDonChiTiet HDCT = readFromResultSet(rs);
                    lHDCT.add(HDCT);
                }
            } finally {
                rs.getStatement();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lHDCT;
    }

    private HoaDonChiTiet readFromResultSet(ResultSet rs) throws SQLException {
        HoaDonChiTiet HDCT = new HoaDonChiTiet();
        HDCT.setId(rs.getInt("ID"));
        HDCT.setMaHD(rs.getInt("MaHD"));
        HDCT.setMaSP(rs.getInt("MaSP"));
        HDCT.setSoLuong(rs.getInt("SoLuong"));
        HDCT.setGiaBan(rs.getFloat("GiaBan"));
        return HDCT;
    }
}

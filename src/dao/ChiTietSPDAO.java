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

    public void insert(ChiTietSP CTSP) {
        String sql = "INSERT INTO ChiTietSP (MaSP, MaMS, MaKT, Hinh)\n"
                + "VALUES (?, ?, ?, ?)";
        Xjdbc.update(sql, CTSP.getMaSP(), CTSP.getMaMS(), CTSP.getMaKT(), CTSP.getHinh());
    }

    public void update(ChiTietSP CTSP) {
        String sql = "UPDATE ChiTietSP SET MaSP=?, MaMS=?, MaKT=?, Hinh=?\n"
                + "WHERE ID=?";
        Xjdbc.update(sql, CTSP.getMaSP(), CTSP.getMaMS(), CTSP.getMaKT(), CTSP.getHinh(), CTSP.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM ChiTietSP WHERE ID=?";
        Xjdbc.update(sql, id);
    }

    public List<ChiTietSP> select() {
        String sql = "SELECT * FROM ChiTietSP";
        return selectBySql(sql);
    }

    public ChiTietSP selectById(int id) {
        String sql = "SELECT * FROM ChiTietSP WHERE ID=?";
        List<ChiTietSP> lCTSP = selectBySql(sql, id);
        return lCTSP.size() > 0 ? lCTSP.get(0) : null;
    }

    public List<ChiTietSP> selectByMaSP(int maSP) {
        String sql = "SELECT * FROM ChiTietSP WHERE MaSP = ?";
        return selectBySql(sql, maSP);
    }

    private List<ChiTietSP> selectBySql(String sql, Object... args) {
        List<ChiTietSP> lCTSP = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Xjdbc.query(sql, args);
                while (rs.next()) {
                    ChiTietSP km = readFromResultSet(rs);
                    lCTSP.add(km);
                }
            } finally {
                rs.getStatement();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lCTSP;
    }

    private ChiTietSP readFromResultSet(ResultSet rs) throws SQLException {
        ChiTietSP lCTSP = new ChiTietSP();
        lCTSP.setId(rs.getInt("ID"));
        lCTSP.setMaSP(rs.getInt("MaSP"));
        lCTSP.setMaMS(rs.getInt("MaMS"));
        lCTSP.setMaKT(rs.getInt("MaKT"));
        lCTSP.setHinh(rs.getString("Hinh"));
        return lCTSP;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.DanhMuc;
import utils.Xjdbc;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DanhMucDAO {

    private final String SELECT_ALL = "SELECT * FROM DanhMuc";
    private final String SELECT_BY_ID = "SELECT * FROM DanhMuc WHERE MaDM = ?";
    private final String INSERT = "INSERT INTO DanhMuc (TenDM) values ?";
    private final String UPDATE = "UPDATE DanhMuc SET TenDM = ? WHERE MaDM = ?";
    private final String DELETE = "DELETE FROM DanhMuc WHERE MaDM = ?";

    //Insert vào danh mục
    public void insert(DanhMuc entity) {
        Xjdbc.update(INSERT,
                entity.getDanhMuc()
        );
    }

    //Update vào danh mục
    public void update(DanhMuc entity) {
        Xjdbc.update(UPDATE,
                entity.getDanhMuc(),
                entity.getMaDM()
        );
    }

    //Delete danh mục
    public void delete(int id) {
        Xjdbc.update(DELETE, id);
    }

    //Select danh mục theo câu lệnh sql
    public List<DanhMuc> SelectBySql(String sql, Object... args) {
        List<DanhMuc> list = new ArrayList<>();
        try (ResultSet rs = Xjdbc.query(sql, args)) {
            while (rs.next()) {
                DanhMuc entity = new DanhMuc();
                entity.setMaDM(rs.getInt("MaDM"));
                entity.setDanhMuc(rs.getString("TenDM"));
                list.add(entity);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return list;
    }

    //Select toàn bộ danh mục
    public List<DanhMuc> selectAll() {
        return SelectBySql(SELECT_ALL);
    }

    //Select danh mục theo mã
    public DanhMuc selectById(int id) {
        return SelectBySql(SELECT_BY_ID, id).get(0);
    }
}

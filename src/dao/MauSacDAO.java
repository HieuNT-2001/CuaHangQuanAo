/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.MauSac;
import utils.Xjdbc;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class MauSacDAO {

    private final String SELECT_ALL = "SELECT * FROM MauSac";
    private final String SELECT_BY_ID = "SELECT * FROM MauSac WHERE MaMS = ?";
    private final String INSERT = "INSERT INTO MauSac (MauSac) values (?)";
    private final String UPDATE = "UPDATE MauSac SET MauSac = ? WHERE MaMS = ?";
    private final String DELETE = "DELETE FROM MauSac WHERE MaMS = ?";

    //Insert vào bảng màu sắc
    public void insert(MauSac entity) {
        Xjdbc.update(INSERT,
                entity.getMauSac()
        );
    }

    //Update vào bảng màu sắc
    public void update(MauSac entity) {
        Xjdbc.update(UPDATE,
                entity.getMauSac(),
                entity.getMaMS()
        );
    }

    //Delete màu sắc
    public void delete(int id) {
        Xjdbc.update(DELETE, id);
    }

    //Select màu sắc theo câu lệnh sql
    public List<MauSac> SelectBySql(String sql, Object... args) {
        List<MauSac> list = new ArrayList<>();
        try (ResultSet rs = Xjdbc.query(sql, args)) {
            while (rs.next()) {
                MauSac entity = new MauSac();
                entity.setMaMS(rs.getInt("MaMS"));
                entity.setMauSac(rs.getString("MauSac"));
                list.add(entity);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return list;
    }

    //Select toàn bộ màu sắc
    public List<MauSac> SelectAll() {
        return SelectBySql(SELECT_ALL);
    }

    //Select màu sắc theo mã
    public MauSac SelectById(int id) {
        return SelectBySql(SELECT_BY_ID, id).get(0);
    }
}

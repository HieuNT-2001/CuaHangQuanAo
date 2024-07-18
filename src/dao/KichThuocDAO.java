/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.KichThuoc;
import utils.Xjdbc;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author PC
 */
public class KichThuocDAO {
    private final String SELECT_ALL = "SELECT * FROM KichThuoc";
    private final String SELECT_BY_ID = "SELECT * FROM KichThuoc WHERE MaKT";
    private final String INSERT = "INSERT INTO KichThuoc (KichThuoc) values ?";
    private final String UPDATE = "UPDATE KichThuoc SET KichThuoc = ? WHERE MaKT = ?";
    private final String DELETE = "DELETE FROM KichThuoc WHERE MaKT = ?";
    
    //Insert vào bảng kích thước
    public void insert(KichThuoc entity){
        Xjdbc.executeUpdate(INSERT, 
                entity.getKichThuoc());
    }
  
    //Update vào bảng kích thước
    public void update(KichThuoc entity){
        Xjdbc.executeUpdate(UPDATE, 
                entity.getKichThuoc());
    }
    
    //Delete kích thước
    public void delete(int id) {
        Xjdbc.executeUpdate(DELETE, id);
    }
    
    //Select màu sắc theo câu lệnh sql
    public List<KichThuoc> SelectBySql(String sql, Object...args){
        List<KichThuoc> list = new ArrayList<>();
        try (ResultSet rs = Xjdbc.executeQuery(sql, args)){
            while(rs.next()) {
                KichThuoc entity = new KichThuoc();
                entity.setMaKT(rs.getInt("MaKT"));
                entity.setKichThuoc(rs.getString("KichThuoc"));
                list.add(entity);
            }
        } catch (SQLException e) {
            System.out.println(e); 
            throw new RuntimeException(e);
        }
        return list;
    }
    
    //Select toàn bộ màu sắc
    public List<KichThuoc> SelectAll(){
        return SelectBySql(SELECT_ALL);
    }
    
    //Select kích thước theo mã
    public KichThuoc SelectById(int id) {
        return SelectBySql(SELECT_BY_ID, id).get(0);
    }
}

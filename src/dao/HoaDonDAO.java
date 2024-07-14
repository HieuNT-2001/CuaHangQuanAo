package dao;

import entity.HoaDon;
import utils.Xjdbc;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonDAO {

    private final String SELECT_ALL = "SELECT * FROM HoaDon";
    private final String SELECT_BY_ID = "SELECT * FROM HoaDon WHERE MaHD = ?";
    private final String SELECT_BY_STATUS = "SELECT * FROM HoaDon WHERE TrangThai = ?";
    private final String INSERT = "INSERT INTO HoaDon (MaNV, TenKH, SDT, DiaChi, MaGiamGia, KenhBanHang, HT_ThanhToan, ThanhTien, NgayTao, TrangThai, LyDo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE HoaDon SET MaNV = ?, TenKH = ?, SDT = ?, DiaChi = ?, MaGiamGia = ?, KenhBanHang = ?, HT_ThanhToan = ?, ThanhTien = ?, NgayTao = ?, TrangThai = ?, LyDo = ? WHERE MaHD = ?";
    private final String DELETE = "DELETE FROM HoaDon WHERE MaHD = ?";

    public void insert(HoaDon entity) {
        Xjdbc.executeUpdate(
                INSERT,
                entity.getMaNV(),
                entity.getTenKH(),
                entity.getSdt(),
                entity.getDiaChi(),
                entity.getMaGiamGia(),
                entity.isKenhBanHang(),
                entity.isHt_thanhToan(),
                entity.getThanhTien(),
                entity.getNgayTao(),
                entity.getTrangThai(),
                entity.getLyDo()
        );
    }

    public void update(HoaDon entity) {
        Xjdbc.executeUpdate(
                UPDATE,
                entity.getMaNV(),
                entity.getTenKH(),
                entity.getSdt(),
                entity.getDiaChi(),
                entity.getMaGiamGia(),
                entity.isKenhBanHang(),
                entity.isHt_thanhToan(),
                entity.getThanhTien(),
                entity.getNgayTao(),
                entity.getTrangThai(),
                entity.getLyDo(),
                entity.getMaHD()
        );
    }

    public void delete(int id) {
        Xjdbc.executeUpdate(DELETE, id);
    }

    public List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try (ResultSet rs = Xjdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getInt("MaHD"));
                entity.setMaNV(rs.getInt("MaNV"));
                entity.setTenKH(rs.getString("TenKH"));
                entity.setSdt(rs.getString("SDT"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setMaGiamGia(rs.getString("MaGiamGia"));
                entity.setKenhBanHang(rs.getBoolean("KenhBanHang"));
                entity.setHt_thanhToan(rs.getBoolean("HT_ThanhToan"));
                entity.setThanhTien(rs.getDouble("ThanhTien"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                entity.setTrangThai(rs.getInt("TrangThai"));
                entity.setLyDo(rs.getString("LyDo"));
                list.add(entity);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<HoaDon> selectAll() {
        return selectBySql(SELECT_ALL);
    }

    public HoaDon selectById(int id) {
        return selectBySql(SELECT_BY_ID, id).get(0);
    }

    public List<HoaDon> selectByStatus(int status) {
        return selectBySql(SELECT_BY_STATUS, status);
    }

}

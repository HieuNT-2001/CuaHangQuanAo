package dao;

import utils.Xjdbc;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ThongKeDAO {

    public double getDoanhThuToday() {
        String sql = "{CALL get_doanhThu_today}";
        return getDoanhThu(sql);
    }

    public double getDoanhThuByDate(Date start, Date end) {
        String sql = "{CALL get_doanhThu_byDate(?, ?)}";
        return getDoanhThu(sql, start, end);
    }

    public double getDoanhThuThisMonth() {
        String sql = "{CALL get_doanhThu_thisMonth}";
        return getDoanhThu(sql);
    }

    public double getDoanhThuThisYear() {
        String sql = "{CALL get_doanhThu_thisYear}";
        return getDoanhThu(sql);
    }

    public List<Object[]> getDoanhThuDetail() {
        String sql = "{CALL get_doanhThu_detail}";
        String[] cols = {"Tháng", "Số lượng bán", "Doanh thu"};
        return getListArray(sql, cols);
    }

    public List<Object[]> getTop10SanPham(int month, int year) {
        String sql = "{CALL get_top10_sanPham(?, ?)}";
        String[] cols = {"Mã sản phẩm", "Tên sản phẩm", "Nhà cung cấp", "Số lượng bán"};
        return getListArray(sql, cols, month, year);
    }

    private double getDoanhThu(String sql, Object... args) {
        try (ResultSet rs = Xjdbc.executeQuery(sql, args)) {
            return rs.getDouble("DoanhThu");
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    private List<Object[]> getListArray(String sql, String[] cols, Object... args) {
        List<Object[]> list = new ArrayList<>();
        try (ResultSet rs = Xjdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = cols[i];
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}

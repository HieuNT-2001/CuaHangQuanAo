package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Xjdbc {

    private final static String URL
            = "jdbc:sqlserver://localhost:1433;"
            + "database=PRO1041_CuaHangQuanAo;"
            + "user=sa;"
            + "password=123456;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=30;";

    private static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
//PrepareStatement
    public static PreparedStatement getStmt(Connection con, String sql, Object... args) throws SQLException {
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = con.prepareCall(sql); // PROC
        } else {
            stmt = con.prepareStatement(sql); //SQL
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }
//Truy vấn (Select) hoặc truy vấn SP
    public static ResultSet executeQuery(String sql, Object... args) {
        try (Connection con = getConnect(); PreparedStatement stmt = getStmt(con, sql, args)) {
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
//Insert, Update, Delete
    public static void executeUpdate(String sql, Object... args) {
        try (Connection con = getConnect(); PreparedStatement stmt = getStmt(con, sql, args)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}

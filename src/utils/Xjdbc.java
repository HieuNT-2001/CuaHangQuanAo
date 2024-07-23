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
            + "trustServerCertificate=true;";
//            + "loginTimeout=30;";

    private static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    //PrepareStatement
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection con = getConnect();
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
    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement stmt = getStmt(sql, args);
        return stmt.executeQuery();
    }

    //Insert, Update, Delete
    public static int update(String sql, Object... args) {
        try (PreparedStatement stmt = getStmt(sql, args)) {
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    
}

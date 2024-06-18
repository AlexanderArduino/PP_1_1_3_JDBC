package jm.task.core.jdbc.util;

import com.mysql.cj.ServerVersion;
import com.mysql.cj.Session;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.*;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import com.mysql.cj.protocol.ServerSessionStateController;

import java.sql.*;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.NClob;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Util {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/database";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "root";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.printf("\nConnection to  %s opened", DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось подключиться к БД");
        }
        return conn;
    }


}

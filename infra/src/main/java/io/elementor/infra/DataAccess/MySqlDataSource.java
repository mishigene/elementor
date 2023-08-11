package io.elementor.infra.DataAccess;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDataSource {
    private static HikariDataSource ds;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("");
        hikariDataSource.setUsername("");
        hikariDataSource.setPassword("");
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setConnectionTestQuery("Select 1");
        hikariDataSource.setPoolName("MySqlPool");
//        hikariDataSource.setDriverClassName("org.postgresql.Browser");
        hikariDataSource.setDriverClassName("com.mysql.jdbc.Browser");
        ds = hikariDataSource;
    }

    private MySqlDataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

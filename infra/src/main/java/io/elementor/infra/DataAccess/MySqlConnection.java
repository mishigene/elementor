package io.elementor.infra.DataAccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class MySqlConnection {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static MySqlConnection instance;

    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    public String dBGetItemNameById(String tableName, int id) {
        Connection conn = null;
        String name = "";
        try {
            String query;

            conn = MySqlDataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            query = String.format("SELECT name FROM %s  WHERE id =%s;", tableName, id);
            logger.info("Sending Query to Trayz DB: " + query );
            rs = stmt.executeQuery(query);
            rs.next();
            name = rs.getString("name");

        } catch (Exception e) {
            logger.error(e.getMessage()+"\n"+ Arrays.toString(e.getStackTrace()));
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error(e.getMessage()+"\n"+ Arrays.toString(e.getStackTrace()));
            }
        }
        return name;
    }
}

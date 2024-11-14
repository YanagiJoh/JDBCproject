package com.jdbclearning.senior.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jdbclearning.advanced.pool.DruidTest;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

    private static DataSource dataSource;

    static {

        try {
            Properties properties = new Properties();

            InputStream inputStream = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);

            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() {

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void release(Connection connection) {

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

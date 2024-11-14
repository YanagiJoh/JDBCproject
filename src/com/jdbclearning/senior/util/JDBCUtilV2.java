package com.jdbclearning.senior.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jdbclearning.advanced.pool.DruidTest;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtilV2 {

    private static DataSource dataSource;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

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

            Connection connection = threadLocal.get();

            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }

            return connection;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void release() {

        try {
            Connection connection = threadLocal.get();

            if (connection != null) {
                threadLocal.remove();
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

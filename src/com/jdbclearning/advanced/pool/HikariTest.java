package com.jdbclearning.advanced.pool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariTest {

    @Test
    public void testHardCodeHikari() throws SQLException {

        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql:///employee");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("123456");

        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setMaximumPoolSize(20);

        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection);

        connection.close();

    }

    @Test
    public void testResourcesHikari() throws Exception {

        Properties properties = new Properties();

        InputStream inputStream = HikariTest.class.getClassLoader().getResourceAsStream("hikari.properties");
        properties.load(inputStream);

        HikariConfig hikariConfig = new HikariConfig(properties);

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection);

        connection.close();

    }
}

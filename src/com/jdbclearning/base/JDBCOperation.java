package com.jdbclearning.base;

import org.junit.Test;

import java.sql.*;

public class JDBCOperation {

    @Test
    public void testQuerySingleRowAndCol() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) as count FROM t_emp;");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int count = resultSet.getInt("count");
            System.out.println(count);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testQuerySingleRow() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age from t_emp WHERE emp_id = ?");

        preparedStatement.setInt(1, 5);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);

        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testQueryMoreRow() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age from t_emp WHERE emp_age > ?;");

        preparedStatement.setInt(1, 25);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);

        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testInsert() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_emp (emp_name,emp_salary,emp_age) VALUES (?,?,?)");

        preparedStatement.setString(1, "Rose");
        preparedStatement.setDouble(2, 345.67);
        preparedStatement.setInt(3, 28);
        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("成功");
        } else {
            System.out.println("失敗");
        }

        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testUpdate() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_emp SET emp_salary = ? WHERE emp_id = ?");

        preparedStatement.setDouble(1, 888.88);
        preparedStatement.setInt(2, 5);
        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("成功");
        } else {
            System.out.println("失敗");
        }

        preparedStatement.close();
        connection.close();

    }
    @Test
    public void testDelete() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_emp WHERE emp_id = ?");

        preparedStatement.setInt(1, 6);
        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("成功");
        } else {
            System.out.println("失敗");
        }

        preparedStatement.close();
        connection.close();

    }

}

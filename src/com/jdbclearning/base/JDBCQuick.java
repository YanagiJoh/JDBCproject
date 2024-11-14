package com.jdbclearning.base;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCQuick {

    public static void main(String[] args) throws Exception {

        //Class.forName("com.mysql.cj.jdbc.Driver");
        DriverManager.registerDriver(new Driver());

        //String url = "jdbc:mysql://localhost:3306/employee";
        String url = "jdbc:mysql:///employee";
        String username = "root";
        String password = "123456";

        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();

        String sql = "SELECT emp_id,emp_name,emp_salary,emp_age from t_emp";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}

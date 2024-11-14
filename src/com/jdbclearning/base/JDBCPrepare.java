package com.jdbclearning.base;

import java.sql.*;
import java.util.Scanner;

public class JDBCPrepare {

    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age from t_emp where emp_name = ?");

        System.out.println("社員の名前を入力してください： ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        preparedStatement.setString(1,name);
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
}

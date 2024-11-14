package com.jdbclearning.advanced;

import com.jdbclearning.advanced.pojo.Employee;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAdvanced {

    @Test
    public void testORM() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age from t_emp WHERE emp_id = ?");

        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;

        while (resultSet.next()) {
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
        }

        System.out.println(employee);

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testORMList() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age from t_emp");

        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;

        List<Employee> employeeList = new ArrayList<>();

        while (resultSet.next()) {
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);

            employeeList.add(employee);
        }

        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testReturnPK() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee", "root", "123456");

        String sql = "INSERT INTO t_emp (emp_name,emp_salary,emp_age) VALUES (?,?,?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        Employee employee = new Employee(null, "jack", 123.45, 29);

        preparedStatement.setString(1, employee.getEmpName());
        preparedStatement.setDouble(2, employee.getEmpSalary());
        preparedStatement.setInt(3, employee.getEmpAge());

        int result = preparedStatement.executeUpdate();
        ResultSet resultSet = null;

        if (result > 0) {
            System.out.println("成功");

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int empId = resultSet.getInt(1);
                employee.setEmpId(empId);

                System.out.println(employee);
            }
        } else {
            System.out.println("失敗");
        }

        if (resultSet != null) {
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testMoreInsert() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql:///employee?rewriteBatchedStatements=true", "root", "123456");

        String sql = "INSERT INTO t_emp (emp_name,emp_salary,emp_age) VALUES (?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {

            preparedStatement.setString(1, "Mary" + i);
            preparedStatement.setDouble(2, 100.0 + i);
            preparedStatement.setInt(3, 20 + i);

            preparedStatement.addBatch();

        }

        preparedStatement.executeBatch();

        long end = System.currentTimeMillis();

        System.out.println("実行時間: " + (end - start));

        preparedStatement.close();
        connection.close();

    }

}
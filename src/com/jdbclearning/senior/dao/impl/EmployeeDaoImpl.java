package com.jdbclearning.senior.dao.impl;

import com.jdbclearning.senior.dao.BaseDAO;
import com.jdbclearning.senior.dao.EmployeeDao;
import com.jdbclearning.senior.pojo.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl extends BaseDAO implements EmployeeDao {

    @Override
    public List<Employee> selectAll() {
        String sql = "SELECT emp_id empId,emp_name empName,emp_salary empSalary,emp_age empAge from t_emp";
        try {
            return executeQuery(Employee.class, sql, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee selectByEmpId(Integer empId) {
        String sql = "SELECT emp_id empId,emp_name empName,emp_salary empSalary,emp_age empAge from t_emp where emp_id = ?";
        try {
            return executeQueryBean(Employee.class, sql, empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Employee employee) {
        String sql = "INSERT INTO t_emp (emp_name,emp_salary,emp_age) VALUES (?,?,?)";
        try {
            return executeUpdate(sql, employee.getEmpName(), employee.getEmpSalary(), employee.getEmpAge());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Employee employee) {
        String sql = "UPDATE t_emp SET emp_salary = ? WHERE emp_id = ?";
        try {
            return executeUpdate(sql, employee.getEmpSalary(), employee.getEmpId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer empId) {
        String sql = "DELETE FROM t_emp WHERE emp_id = ?";
        try {
            return executeUpdate(sql, empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

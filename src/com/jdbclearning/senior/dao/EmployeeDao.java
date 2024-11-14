package com.jdbclearning.senior.dao;

import com.jdbclearning.senior.pojo.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> selectAll();

    Employee selectByEmpId(Integer empId);

    int insert(Employee employee);

    int update(Employee employee);

    int delete(Integer empId);

}

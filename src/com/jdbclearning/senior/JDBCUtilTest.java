package com.jdbclearning.senior;

import com.jdbclearning.senior.dao.BankDao;
import com.jdbclearning.senior.dao.EmployeeDao;
import com.jdbclearning.senior.dao.impl.BankDaoImpl;
import com.jdbclearning.senior.dao.impl.EmployeeDaoImpl;
import com.jdbclearning.senior.pojo.Employee;
import com.jdbclearning.senior.util.JDBCUtil;
import com.jdbclearning.senior.util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCUtilTest {

    @Test
    public void testGetConnection() {

        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);

        JDBCUtil.release(connection);

    }

    @Test
    public void testJDBCV2() {

//        Connection connection1 = JDBCUtil.getConnection();
//        Connection connection2 = JDBCUtil.getConnection();
//        Connection connection3 = JDBCUtil.getConnection();
//
//        System.out.println(connection1);
//        System.out.println(connection2);
//        System.out.println(connection3);

        Connection connection1 = JDBCUtilV2.getConnection();
        Connection connection2 = JDBCUtilV2.getConnection();
        Connection connection3 = JDBCUtilV2.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);

        JDBCUtilV2.release();

    }

    @Test
    public void testEmployeeDao() {

        EmployeeDao employeeDao = new EmployeeDaoImpl();

//        List<Employee> employeeList = employeeDao.selectAll();
//
//        for (Employee employee : employeeList) {
//
//            System.out.println("employee = " + employee);

//        }

//        Employee employee = employeeDao.selectByEmpId(1);
//        System.out.println(employee);

//        Employee employee = new Employee(null,"Tom",300.65,38);
//        int insert = employeeDao.insert(employee);
//        System.out.println(insert);

//        Employee employee = new Employee(20008,"Tom",656.65,38);
//        int update = employeeDao.update(employee);
//        System.out.println(update);

        int delete = employeeDao.delete(20008);
        System.out.println(delete);

    }

    @Test
    public void testTransaction() {

        BankDao bankDao = new BankDaoImpl();

        Connection connection = null;

        try {

            connection = JDBCUtilV2.getConnection();
            connection.setAutoCommit(false);

            bankDao.subMoney(1, 100);
            int i = 10 / 0;
            bankDao.addMoney(2, 100);

            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            JDBCUtilV2.release();
        }

    }
}

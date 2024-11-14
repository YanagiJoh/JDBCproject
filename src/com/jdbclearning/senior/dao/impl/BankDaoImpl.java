package com.jdbclearning.senior.dao.impl;

import com.jdbclearning.senior.dao.BankDao;
import com.jdbclearning.senior.dao.BaseDAO;

import java.sql.SQLException;

public class BankDaoImpl extends BaseDAO implements BankDao {

    @Override
    public int addMoney(Integer id, Integer money) {
        String sql = "UPDATE t_bank SET money = money + ? where id = ?";
        try {
            return executeUpdate(sql, money, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int subMoney(Integer id, Integer money) {
        String sql = "UPDATE t_bank SET money = money - ? where id = ?";
        try {
            return executeUpdate(sql, money, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

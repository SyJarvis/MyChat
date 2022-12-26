package com.taihua.dao.impl;

import com.taihua.dao.UserDao;
import com.taihua.entity.User;
import com.taihua.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    QueryRunner queryRunner = new QueryRunner();
    @Override
    public int insert(User user) {
        int result = 0;
        try {
            result = queryRunner.update(DbUtils.getConnection(), "insert into user(username, " +
                    "password, status, created_time, updated_time) values (?, ?, ?, ?, ?)",
                    user.getUsername(), user.getPassword(), user.getStatus(),
                    user.getCreated_time(), user.getUpdated_time());
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User select(String username) {
        User user = null;
        try {
            user = queryRunner.query(DbUtils.getConnection(),
                    "select * from user where username=? and status=1",
                    new BeanHandler<>(User.class), username);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try {
            result = queryRunner.update(DbUtils.getConnection(),
                    "update user set status=0 where id=?;", id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = null;
        try {
            users = queryRunner.query(DbUtils.getConnection(),
                    "select * from user where status=1;",
                    new BeanListHandler<>(User.class));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
}

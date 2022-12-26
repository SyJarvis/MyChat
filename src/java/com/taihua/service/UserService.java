package com.taihua.service;

import com.taihua.dao.UserDao;
import com.taihua.dao.impl.UserDaoImpl;
import com.taihua.entity.User;
import com.taihua.utils.DbUtils;

public class UserService {
    UserDao userDao = new UserDaoImpl();
    public User checkUser(String username){
        User user = null;
        try {
            DbUtils.begin();
            user = userDao.select(username);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            throw new RuntimeException(e);
        }
        return user;
    }
    public int register(User user){
        int result = 0;
        try {
            DbUtils.begin();
            result = userDao.insert(user);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            throw new RuntimeException(e);
        }
        return result;
    }


}

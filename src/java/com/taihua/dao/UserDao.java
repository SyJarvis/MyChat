package com.taihua.dao;

import com.taihua.entity.User;

import java.util.List;

public interface UserDao {
    public int insert(User user);
    // 用户名应该只有一个
    public User select(String username);
    public int delete(int id);
    public List<User> selectAll();
}

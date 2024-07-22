package com.michell.users_api.dao;

import com.michell.users_api.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> findAll();
    public User findById(int id);
    public void save(User user);
    public void update(User user);
    public void deleteById(int id);
}

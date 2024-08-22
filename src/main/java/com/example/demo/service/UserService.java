package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public IPage<User> getUsersPage(int page, int size) {
        Page<User> userPage = new Page<>(page, size);
        return userMapper.selectPage(userPage, null);
    }

    public boolean addUser(User user) {
        int rows = userMapper.insert(user);
        return rows > 0;
    }

    public boolean deleteUser(Long id) {
        int rows = userMapper.deleteById(id);
        return rows > 0;
    }
}

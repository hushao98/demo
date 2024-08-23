package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public IPage<User> getUsersPage(int page, int size) {
        Page<User> userPage = new Page<>(page, size);
        return userMapper.selectPage(userPage, null);
    }

    public IPage<User> searchUsers(int page, int size, String keyword) {
        Page<User> userPage = new Page<>(page, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(User::getName, keyword)
                .or().like(User::getPhone, keyword)
                .or().like(User::getEmail, keyword);
        return userMapper.selectPage(userPage, queryWrapper);
    }

    public boolean addUser(User user) {
        user.setId(generateShortId());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        int rows = userMapper.insert(user);
        return rows > 0;
    }

    public boolean updateUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        int rows = userMapper.updateById(user);
        return rows > 0;
    }

    public boolean deleteUser(Long id) {
        int rows = userMapper.deleteById(id);
        return rows > 0;
    }

    private long generateShortId() {
        Random random = new Random();
        long id;
        do {
            long min = 1000000000L; // 10位数的最小值
            long max = 9999999999L; // 10位数的最大值
            id = min + ((long)(random.nextDouble() * (max - min)));
        } while (userMapper.selectById(id) != null); // 检查ID是否存在
        return id;
    }
}

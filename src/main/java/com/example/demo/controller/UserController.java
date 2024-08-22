package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public IPage<User> getUsers(@RequestBody Map<String, Integer> params) {
        int page = params.getOrDefault("page", 1); // 默认值为1
        int size = params.getOrDefault("size", 10); // 默认值为10
        return userService.getUsersPage(page, size);
    }
}

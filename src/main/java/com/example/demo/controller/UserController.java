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
        int page = params.getOrDefault("page", 1);
        int size = params.getOrDefault("size", 10);
        return userService.getUsersPage(page, size);
    }

    @PostMapping("/search")
    public IPage<User> searchUsers(@RequestBody Map<String, Object> params) {
        int page = (int) params.getOrDefault("page", 1);
        int size = (int) params.getOrDefault("size", 10);
        String keyword = (String) params.getOrDefault("keyword", "");
        return userService.searchUsers(page, size, keyword);
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            return "Failed to add user: name is required.";
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "Failed to add user: password is required.";
        }
        boolean result = userService.addUser(user);
        return result ? "User added successfully" : "Failed to add user";
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody User user) {
        if (user.getId() == null) {
            return "Failed to update user: ID is required.";
        }
        boolean result = userService.updateUser(user);
        return result ? "User updated successfully" : "Failed to update user";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean result = userService.deleteUser(id);
        return result ? "User deleted successfully" : "Failed to delete user";
    }
}

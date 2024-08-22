package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("users")
public class User {
    @TableId
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String address;
    private Integer gender;
    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}

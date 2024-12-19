package com.Tp5.Tp5.Dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
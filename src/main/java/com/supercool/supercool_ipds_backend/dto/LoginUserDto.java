package com.supercool.supercool_ipds_backend.dto;

public class LoginUserDto {

    private String userName;

    private String password;

    public LoginUserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginUserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

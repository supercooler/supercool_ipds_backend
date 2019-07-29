package com.supercool.supercool_ipds_backend.controller;

import com.supercool.supercool_ipds_backend.common.response.ResponseEntityUtil;
import com.supercool.supercool_ipds_backend.model.User;
import com.supercool.supercool_ipds_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 1000L)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity loginUser(@RequestBody User user){
        return ResponseEntityUtil.responseSuccess(userService.UserLogin(user.getUserName(),user.getPassword()));
    }
}

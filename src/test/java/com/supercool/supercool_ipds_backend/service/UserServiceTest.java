package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.common.utils.MD5Utils;
import com.supercool.supercool_ipds_backend.dto.LoginUserDto;
import com.supercool.supercool_ipds_backend.model.ParkingLot;
import com.supercool.supercool_ipds_backend.model.User;
import com.supercool.supercool_ipds_backend.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    private void setUp(){
        userService = new UserService();
    }

    @Test
    public void should_return_user_when_call_login() {
        LoginUserDto loginUser = new LoginUserDto("jerryLi", "123456");
        User user = new User("jerryLi", MD5Utils.MD5("123456"),"super");
        user.setId(Long.valueOf(1));
        when(userRepository.findByUserName("jerryLi")).thenReturn(user);
        User result = userService.UserLogin(loginUser);
        verify(userRepository,times(1)).findByUserName("jerryLi");
        assertEquals("super",result.getRole());
    }

    @Test
    public void should_return_user_when_call_register() {
        User user = new User("jerryLi", MD5Utils.MD5("123456"),"super");
        when(userRepository.findByUserName("jerryLi")).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.UserRegister(user);
        assertEquals("super",result.getRole());
    }
}
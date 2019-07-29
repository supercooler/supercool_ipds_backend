package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.common.utils.MD5Utils;
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
        User user = new User("jerryLi", MD5Utils.MD5("134679258"));
        user.setId(Long.valueOf(1));
        when(userRepository.findByUserName("jerryLi")).thenReturn(user);
        User result = userService.UserLogin("jerryLi","134679258");
        verify(userRepository,times(1)).findByUserName("jerryLi");
        assertEquals(user.getId(),result.getId());
    }

    @Test
    public void should_return_exception_when_call_login_given_null() {
        User user = null;
        when(userRepository.findByUserName("jerryLi")).thenReturn(user);
        assertThrows(CustomException.class,()->userService.UserLogin("jerryLi","134679258"));
        verify(userRepository,times(1)).findByUserName("jerryLi");

    }
}
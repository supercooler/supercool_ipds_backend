package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_return_user_with_when_call_findBuyUserName() throws ParseException {
        User user = new User("jerryLi","134679258");
        userRepository.save(user);
        User result = userRepository.findByUserName("jerryLi");
        assertEquals(result.getPassword(),user.getPassword());
    }

}
package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingBoyRepositoryTest {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Test
    public void should_return_parking_boy_with_age_when_call_findAll() throws ParseException {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.setId(1L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        parkingBoy.setBirthYear(simpleDateFormat.parse("1990-01-10 20:10:10"));
        parkingBoy.setEmployeeDate(simpleDateFormat.parse("2000-10-10 10:00:00"));
        parkingBoyRepository.save(parkingBoy);
        List<ParkingBoy> parkingBoys = parkingBoyRepository.findAll();
        assertEquals(29, parkingBoys.get(0).getAge());
    }

}
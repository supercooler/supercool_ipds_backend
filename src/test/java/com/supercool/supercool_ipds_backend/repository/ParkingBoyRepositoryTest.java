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
import java.util.Date;
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
        buildParkingLot(parkingBoy);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        parkingBoy.setBirthYear(simpleDateFormat.parse("1990-01-10 20:10:10"));
        parkingBoy.setEmployeeDate(simpleDateFormat.parse("2000-10-10 10:00:00"));
        parkingBoyRepository.save(parkingBoy);
        List<ParkingBoy> parkingBoys = parkingBoyRepository.findAll();
        assertEquals(29, parkingBoys.get(0).getAge());
    }

    @Test
    public void should_return_parking_boy_with_name_when_call_findAll_given_parking_boy_with_name_samson() throws ParseException {
        ParkingBoy parkingBoy = new ParkingBoy();
        buildParkingLot(parkingBoy);
        parkingBoy.setBirthYear(new Date());
        parkingBoy.setEmployeeDate(new Date());
        parkingBoyRepository.save(parkingBoy);
        parkingBoyRepository.findAll();
        List<ParkingBoy> parkingBoys = parkingBoyRepository.findByNameLike("%"+"test".trim().toUpperCase()+"%");
        assertEquals("test", parkingBoys.get(0).getName());
    }

    @Test
    public void should_return_parking_boy_with_gender_when_call_findAll_given_parking_boy_with_gender() throws ParseException {
        ParkingBoy parkingBoy = new ParkingBoy();
        buildParkingLot(parkingBoy);
        parkingBoy.setEmployeeDate(new Date());
        parkingBoy.setBirthYear(new Date());
        parkingBoyRepository.save(parkingBoy);
        List<ParkingBoy> parkingBoys = parkingBoyRepository.findByGender("male");
        assertEquals("male", parkingBoys.get(0).getGender());
    }

    private void buildParkingLot(ParkingBoy parkingBoy) {
        parkingBoy.setId(1L);
        parkingBoy.setName("test");
        parkingBoy.setPhone("123456");
        parkingBoy.setGender("male");
        parkingBoy.setStatus("busy");
        parkingBoy.setTag("美丽小姐姐");
    }
}

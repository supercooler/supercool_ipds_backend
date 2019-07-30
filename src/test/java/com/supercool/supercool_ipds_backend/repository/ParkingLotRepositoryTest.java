package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.model.ParkingLot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Test
    public void should_return_size_of_boy_when_given_lots_to_boy(){
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot lot1 = new ParkingLot();lot1.setName("lot1");
        ParkingLot lot2 = new ParkingLot();lot2.setName("lot2");
        List<ParkingLot> list = new ArrayList<>();
        list.add(lot1);list.add(lot2);
        parkingBoy = parkingBoyRepository.save(parkingBoy);
        parkingLotRepository.save(lot1);
        parkingLotRepository.save(lot2);
        parkingBoy.setParkingLots(list);
        parkingBoyRepository.save(parkingBoy);
        ParkingBoy result = parkingBoyRepository.findById(parkingBoy.getId()).orElse(null);
        assertEquals(2,result.getParkingLots().size());
    }

}
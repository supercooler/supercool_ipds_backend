package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingOrderRepositoryTest {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Test
    public void should_return_true_size_when_call_get_orders(){
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setCarLisenceNumber("1234567");
        parkingOrder.setPreLocation("南方软件园");
        parkingOrder.setUserPhone("18229797216");
        parkingOrderRepository.save(parkingOrder);
        assertEquals(1,parkingOrderRepository.findAll().size());
    }

    @Test
    public void should_return_true_size_when_call_delete_orders(){
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setCarLisenceNumber("1234567");
        parkingOrder.setPreLocation("南方软件园");
        parkingOrder.setUserPhone("18229797216");
        parkingOrderRepository.save(parkingOrder);
        parkingOrderRepository.deleteById(Long.valueOf(1));
        assertEquals(0,parkingOrderRepository.findAll().size());
    }
}
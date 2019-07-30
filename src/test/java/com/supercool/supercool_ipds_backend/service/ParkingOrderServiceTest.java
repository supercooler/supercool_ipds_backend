package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingOrderServiceTest {

    @Autowired
    private ParkingOrderService parkingOrderService;

    @MockBean
    private ParkingOrderRepository parkingOrderRepository;

    @Test
    public void should_return_true_size_when_call_get_orders(){
        when(parkingOrderRepository.findAll()).thenReturn(new ArrayList<>());
        parkingOrderService.getParkingOrders();
        verify(parkingOrderRepository,times(1)).findAll();
    }

    @Test
    public void should_return_true_size_when_call_delete_orders(){
        parkingOrderService.deleteParkingOrder(Long.valueOf(1));
        verify(parkingOrderRepository,times(1)).deleteById(Long.valueOf(1));
    }
}
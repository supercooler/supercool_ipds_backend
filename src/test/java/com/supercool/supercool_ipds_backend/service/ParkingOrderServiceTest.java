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
        parkingOrderService.getParkingOrders("","","");
        verify(parkingOrderRepository,times(1)).findAll();

        when(parkingOrderRepository.findAllByStatus("已下单")).thenReturn(new ArrayList<>());
        parkingOrderService.getParkingOrders("已下单","","");
        verify(parkingOrderRepository,times(1)).findAllByStatus("已下单");

        when(parkingOrderRepository.findByBoy("%张%")).thenReturn(new ArrayList<>());
        parkingOrderService.getParkingOrders("","张","");
        verify(parkingOrderRepository,times(1)).findByBoy("%张%");

        when(parkingOrderRepository.findByLot("%南%")).thenReturn(new ArrayList<>());
        parkingOrderService.getParkingOrders("","","南");
        verify(parkingOrderRepository,times(1)).findByLot("%南%");

        when(parkingOrderRepository.findByUser(1L)).thenReturn(new ArrayList<>());
        parkingOrderService.getOrdersByUserId(1L);
        verify(parkingOrderRepository,times(1)).findByUser(1L);
    }

    @Test
    public void should_return_true_size_when_call_delete_orders(){
        parkingOrderService.deleteParkingOrder(Long.valueOf(1));
        verify(parkingOrderRepository,times(1)).deleteById(Long.valueOf(1));
    }
}
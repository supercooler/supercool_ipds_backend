package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.model.ParkingLot;
import com.supercool.supercool_ipds_backend.repository.ParkingLotRepository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotServiceTest {

    @Autowired
    private ParkingLotService parkingLotService;

    @MockBean
    private ParkingLotRepository parkingLotRepository;

    @Test
    public void should_return_http_ok_when_call_create_parkLot() {
        ParkingLot parkingLot = new ParkingLot();
        given(parkingLotRepository.save(parkingLot)).willReturn(null);

        parkingLotService.createParkingLot(parkingLot);
        verify(parkingLotRepository, times(1)).save(parkingLot);

    }

    @Test
    public void should_return_parking_lots_when_call_find_all_parkLots() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        when(parkingLotRepository.findAll()).thenReturn(parkingLots);

        List<ParkingLot> results =  parkingLotService.getParkingLots();

        Assertions.assertEquals(1, results.size());
    }


    @Test
    public void should_return_parking_lots_when_call_find_all_parkLots_by_name() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        when(parkingLotRepository.findAllByName("Ethan")).thenReturn(parkingLots);

        List<ParkingLot> results =  parkingLotService.findParkingLotByName("Ethan");

        Assertions.assertEquals(1, results.size());
    }
}

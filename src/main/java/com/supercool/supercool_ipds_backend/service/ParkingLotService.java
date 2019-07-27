package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.model.ParkingLot;
import com.supercool.supercool_ipds_backend.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    public void createParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLotRepository.findAll();
    }

    public List<ParkingLot> findParkingLotByName(String name) {
        return parkingLotRepository.findAllByName(name);
    }
}

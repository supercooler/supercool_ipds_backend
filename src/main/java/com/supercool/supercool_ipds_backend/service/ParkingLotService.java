package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.model.ParkingLot;
import com.supercool.supercool_ipds_backend.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    public void createParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
    }

    public List<ParkingLot> getParkingLots(int current, int size) {
        Page resultPage = parkingLotRepository.findAll(PageRequest.of(current, size));
        return resultPage.getContent();
    }

    public List<ParkingLot> findParkingLotByName(String name) {
        return parkingLotRepository.findByNameLike(name);
    }

    public void updateParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
    }

    public void deleteParkingLotById(Long id) {
        parkingLotRepository.deleteById(id);
    }


    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }
}

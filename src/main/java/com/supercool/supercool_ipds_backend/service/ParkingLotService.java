package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.model.ParkingLot;
import com.supercool.supercool_ipds_backend.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum.Delete_Not_Found_Exception;

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
        try {
            parkingLotRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(Delete_Not_Found_Exception.getMessage(), Delete_Not_Found_Exception.getCode());
        }
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }
}

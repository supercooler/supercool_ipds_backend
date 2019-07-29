package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.repository.ParkingBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum.Delete_Not_Found_Exception;

@Service
public class ParkingBoyService {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    public List<ParkingBoy> getParkingBoys(String name, String gender) {
        if (name != null) return parkingBoyRepository.findByNameLike("%" + name.trim().toUpperCase() + "%");
        if (gender != null) return parkingBoyRepository.findByGender(gender);
        return parkingBoyRepository.findAllByOrderByBirthYearDesc();
    }

    public ParkingBoy updateParkingBoy(ParkingBoy parkingBoy) {
        return parkingBoyRepository.save(parkingBoy);
    }

    public ParkingBoy addParkingBoy(ParkingBoy parkingBoy) {
        return parkingBoyRepository.save(parkingBoy);
    }

    public void deleteParkingBoy(Long id) {
        try {
            parkingBoyRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(Delete_Not_Found_Exception.getMessage(), Delete_Not_Found_Exception.getCode());
        }

    }
}

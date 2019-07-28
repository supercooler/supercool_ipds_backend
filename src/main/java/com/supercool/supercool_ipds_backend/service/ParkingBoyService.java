package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.repository.ParkingBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingBoyService {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    public List<ParkingBoy> getParkingBoys(String name,String gender) {
        if(name!=null)return parkingBoyRepository.findByNameLikeIgnoreCase("%"+name.trim().toUpperCase()+"%");
        if(gender!=null)return parkingBoyRepository.findByGender(gender);
        return parkingBoyRepository.findAll();
    }
}

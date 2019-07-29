package com.supercool.supercool_ipds_backend.common.utils;

import com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.repository.ParkingBoyRepository;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DistributionExcellentParkingBoy {

    private static final int BASE = 500;

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    private static DistributionExcellentParkingBoy distributionExcellentParkingBoy;

    @PostConstruct
    public void init(){
        distributionExcellentParkingBoy=this;
    }

    private double calculteParkingBoyScore(double userGivenScore, Long count) {
        return userGivenScore + count * 1.0 / BASE;
    }

    public ParkingBoy getExcellentParkingBoy(){
        List<ParkingOrderDO> parkingOrderDOs = distributionExcellentParkingBoy.parkingOrderRepository.loadParkingOrderDOs();
        ParkingOrderDO parkingOrderDO = parkingOrderDOs.stream().reduce((a, b) -> {
            if(calculteParkingBoyScore(a.getScore(), a.getCount()) > calculteParkingBoyScore(b.getScore(), b.getCount()))
                return a;
            return b;
        }).orElse(null);
        return distributionExcellentParkingBoy.parkingBoyRepository.findById(parkingOrderDO.getParkingBoyId()).orElse(null);
    }
}

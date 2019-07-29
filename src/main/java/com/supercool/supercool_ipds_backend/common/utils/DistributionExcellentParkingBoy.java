package com.supercool.supercool_ipds_backend.common.utils;

import com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.repository.ParkingBoyRepository;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DistributionExcellentParkingBoy {

    private static final int PARKING_TIMES_RATE_BASE = 500;
    private static final double PARKING_ORDER_RATE = 0.001;

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    private static DistributionExcellentParkingBoy distributionExcellentParkingBoy;

    @PostConstruct
    public void init(){
        distributionExcellentParkingBoy=this;
    }

    private double calculateParkingBoyScore(double userGivenScore, Long count) {
        System.out.println(Math.random() + PARKING_ORDER_RATE * (userGivenScore + count * 1.0 / PARKING_TIMES_RATE_BASE));
        return Math.random() + PARKING_ORDER_RATE * (userGivenScore + count * 1.0 / PARKING_TIMES_RATE_BASE);
    }

    public ParkingBoy getExcellentParkingBoy(){
        List<ParkingOrderDO> parkingOrderDOs = distributionExcellentParkingBoy.parkingOrderRepository.loadParkingOrderDOs();
        ParkingOrderDO parkingOrderDO = parkingOrderDOs.stream().reduce((a, b) -> {
            if(calculateParkingBoyScore(a.getScore(), a.getCount()) > calculateParkingBoyScore(b.getScore(), b.getCount()))
                return a;
            return b;
        }).orElse(null);
        return distributionExcellentParkingBoy.parkingBoyRepository.findById(parkingOrderDO.getParkingBoyId()).orElse(null);
    }
}

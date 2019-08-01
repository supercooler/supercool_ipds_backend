package com.supercool.supercool_ipds_backend.common.utils;

import com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO;
import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import com.supercool.supercool_ipds_backend.repository.ParkingBoyRepository;
import com.supercool.supercool_ipds_backend.repository.ParkingLotRepository;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderDORepository;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum.*;

@Component
public class DistributionExcellentParkingBoy {

    private static final int PARKING_TIMES_RATE_BASE = 500;
    private static final int JUDGE_PARKING_BOY_BUSY_TIME_DEGREE = 600000;

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingOrderDORepository parkingOrderDORepository;

    private static DistributionExcellentParkingBoy distributionExcellentParkingBoy;

    @PostConstruct
    public void init() {
        distributionExcellentParkingBoy = this;
    }

    private double calculateParkingBoyScore(double userGivenScore, Long count) {
        return userGivenScore + count * 1.0 / PARKING_TIMES_RATE_BASE;
    }

    public ParkingBoy getExcellentParkingBoy(ParkingOrder parkingOrder) {
        //validate parking order
        if (parkingOrder == null) {
            throw new CustomException(SHOULD_INPUT_VALID_PARKING_ORDER.getMessage(), SHOULD_INPUT_VALID_PARKING_ORDER.getCode());
        }

        //parking boy table is null
        List<ParkingBoy> parkingBoys = distributionExcellentParkingBoy.parkingBoyRepository.findAll();
        boolean isParkingBoyTableEmpty = parkingBoys == null || parkingBoys.size() == 0;
        if (isParkingBoyTableEmpty)
            throw new CustomException(Parking_Boy_Not_Exist_Exception.getMessage(), Parking_Boy_Not_Exist_Exception.getCode());

        //all parking lots' rest capacity = 0
        Long allParkingLotsRestCapacity = distributionExcellentParkingBoy.parkingLotRepository.getAllRestCapacity();
        if (allParkingLotsRestCapacity == null || allParkingLotsRestCapacity <= 0) {
            throw new CustomException(ALL_PARKING_LOTS_REST_CAPACITY_IS_ZERO.getMessage(), ALL_PARKING_LOTS_REST_CAPACITY_IS_ZERO.getCode());
        }

        //parking order table is null,noted parking boy has no parking lot
        List<ParkingOrder> parkingOrders = distributionExcellentParkingBoy.parkingOrderRepository.findAll();
        boolean isParkingOrderTableEmpty = parkingOrders == null || parkingOrders.size() == 0;
        if (isParkingOrderTableEmpty) {
            List<ParkingBoy> parkingBoysWithParkingLots = parkingBoys.stream().filter(item -> item.getParkingLots().size() > 0).collect(Collectors.toList());
            if (parkingBoysWithParkingLots == null || parkingBoysWithParkingLots.size() == 0) {
                throw new CustomException(ALL_PARKING_LOTS_REST_CAPACITY_IS_ZERO.getMessage(), ALL_PARKING_LOTS_REST_CAPACITY_IS_ZERO.getCode());
            }
            return parkingBoysWithParkingLots.get((int) (Math.random() * parkingBoysWithParkingLots.size()));
        }

        //all parking boys busy
        Date floor = new Date(parkingOrder.getBookTime().getTime() - JUDGE_PARKING_BOY_BUSY_TIME_DEGREE);
        Date ceil = new Date(parkingOrder.getBookTime().getTime() + JUDGE_PARKING_BOY_BUSY_TIME_DEGREE);
        int parkingBoysCount = parkingBoys.size();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int busyParkingBoysCount = distributionExcellentParkingBoy.parkingOrderRepository.findAllParkingBoysBusy(simpleDateFormat.format(ceil), simpleDateFormat.format(floor));
        if (parkingBoysCount == busyParkingBoysCount) return null;

        //parking boy and order table is not null
        List<ParkingOrderDO> parkingOrderDOs = distributionExcellentParkingBoy.parkingOrderDORepository.loadParkingOrderDOs();
        if (parkingOrderDOs == null || parkingOrderDOs.isEmpty()) return null;
        ParkingOrderDO parkingOrderDO = parkingOrderDOs.stream().reduce((a, b) -> {
            if (calculateParkingBoyScore(a.getScore(), a.getCount()) - calculateParkingBoyScore(b.getScore(), b.getCount()) >= 0.001)
                return a;
            return b;
        }).orElse(null);
        return distributionExcellentParkingBoy.parkingBoyRepository.findById(parkingOrderDO.getParkingBoyId()).orElse(null);
    }
}

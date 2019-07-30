package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.constant.Constant;
import com.supercool.supercool_ipds_backend.common.utils.DistributionExcellentParkingBoy;
import com.supercool.supercool_ipds_backend.dto.AppointmentDto;
import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import com.supercool.supercool_ipds_backend.repository.ParkingBoyRepository;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum.*;

@Service
public class ParkingOrderService {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private DistributionExcellentParkingBoy distributionExcellentParkingBoy;

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    public List<ParkingOrder> getParkingOrders(String status,String boy,String lot) {
        if(status.equalsIgnoreCase("")){
            if (boy.equalsIgnoreCase("")){
                if (lot.equalsIgnoreCase("")){
                    return parkingOrderRepository.findAll();
                }
                else {
                    return parkingOrderRepository.findByLot("%" + lot.trim() + "%");
                }
            }
            else {
                return parkingOrderRepository.findByBoy("%" + boy.trim() + "%");
            }
        }
        else{
            return parkingOrderRepository.findAllByStatus(status);
        }
    }

    public ParkingOrder createParkingOrders(AppointmentDto appointmentDto) {
        ParkingOrder parkingOrder = generateParkingOrder(appointmentDto);
        return parkingOrderRepository.save(parkingOrder);
    }

    private ParkingOrder generateParkingOrder(AppointmentDto appointmentDto) {
        ParkingBoy parkingBoy = distributionExcellentParkingBoy.getExcellentParkingBoy();
        if (parkingBoy!=null) {
            ParkingOrder parkingOrder = new ParkingOrder();
            parkingOrder.setCarLisenceNumber(appointmentDto.getPlateNumber());
            parkingOrder.setPreLocation(appointmentDto.getAddress());
            parkingOrder.setUserPhone(appointmentDto.getPhone());
            parkingOrder.setBookTime(appointmentDto.getBookTime());
            parkingOrder.setParkingBoy(parkingBoy);
            return parkingOrder;
        }else{
            throw new CustomException(Parking_Boy_Busy_Exception.getMessage(), Parking_Boy_Busy_Exception.getCode());
        }

//        ParkingOrder parkingOrder = new ParkingOrder();
////        parkingOrder.setParkingBoy(distributionExcellentParkingBoy.getExcellentParkingBoy());
//        ParkingBoy parkingBoy = parkingBoyRepository.findById(1L).orElse(new ParkingBoy());
//        parkingOrder.setCarLisenceNumber(appointmentDto.getPlateNumber());
//        parkingOrder.setPreLocation(appointmentDto.getAddress());
//        parkingOrder.setUserPhone(appointmentDto.getPhone());
//        parkingOrder.setBookTime(appointmentDto.getBookTime());
//        parkingOrder.setParkingBoy(parkingBoy);
//        return parkingOrder;

    }

    public void deleteParkingOrder(Long id) {
        try {
            parkingOrderRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(Delete_Not_Found_Exception.getMessage(), Delete_Not_Found_Exception.getCode());
        }
    }

    public ParkingOrder updateParkingOrder(ParkingOrder parkingOrder) {
        try {
            parkingOrder.setStatus(Constant.IN_FETCHING_CAR);
            return parkingOrderRepository.save(parkingOrder);
        } catch (Exception e) {
            throw new CustomException(Update_Not_Fount_Exception.getMessage(), Update_Not_Fount_Exception.getCode());
        }
    }

    public List<ParkingOrder> getOrdersByUserId(Long id) {
        return parkingOrderRepository.findByUser(id);
    }
}

package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.dto.AppointmentDto;
import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum.Delete_Not_Found_Exception;

@Service
public class ParkingOrderService {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    public List<ParkingOrder> getParkingOrders() {
        return parkingOrderRepository.findAll();
    }

    public ParkingOrder createParkingOrders(AppointmentDto appointmentDto) {
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setCarLisenceNumber(appointmentDto.getPlateNumber());
        parkingOrder.setPreLocation(appointmentDto.getAddress());
        parkingOrder.setUserPhone(appointmentDto.getPhone());
        return parkingOrderRepository.save(parkingOrder);
        }
        
    public void deleteParkingOrder(Long id) {
        try {
            parkingOrderRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(Delete_Not_Found_Exception.getMessage(), Delete_Not_Found_Exception.getCode());
        }
    }
}

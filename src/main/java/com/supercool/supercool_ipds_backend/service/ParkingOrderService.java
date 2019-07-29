package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.dto.AppointmentDto;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

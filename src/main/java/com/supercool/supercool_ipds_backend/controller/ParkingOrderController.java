package com.supercool.supercool_ipds_backend.controller;

import com.supercool.supercool_ipds_backend.common.response.ResponseEntityUtil;
import com.supercool.supercool_ipds_backend.dto.AppointmentDto;
import com.supercool.supercool_ipds_backend.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parking-orders")
@CrossOrigin(origins = "*", maxAge = 1000L)
public class ParkingOrderController {

    @Autowired
    private ParkingOrderService parkingOrderService;

    @GetMapping
    public ResponseEntity getParkingOrders(){
        return ResponseEntityUtil.responseSuccess(parkingOrderService.getParkingOrders());
    }

    @PostMapping
    public ResponseEntity createParkingOrders(@RequestBody AppointmentDto appointmentDto){
        return ResponseEntityUtil.responseSuccess(parkingOrderService.createParkingOrders(appointmentDto));
    }

    @DeleteMapping
    public ResponseEntity deleteParkingOrder(@RequestParam Long id){
        parkingOrderService.deleteParkingOrder(id);
        return ResponseEntityUtil.responseSuccess(HttpStatus.OK);
    }

}

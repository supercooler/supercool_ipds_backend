package com.supercool.supercool_ipds_backend.controller;

import com.supercool.supercool_ipds_backend.common.response.ResponseEntityUtil;
import com.supercool.supercool_ipds_backend.common.utils.DistributionExcellentParkingBoy;
import com.supercool.supercool_ipds_backend.dto.AppointmentDto;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
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
    public ResponseEntity getParkingOrders(
            @RequestParam(value = "status", required = false, defaultValue = "") String status,
            @RequestParam(value = "boy", required = false, defaultValue = "") String boy,
            @RequestParam(value = "lot", required = false, defaultValue = "") String lot) {
        return ResponseEntityUtil.responseSuccess(parkingOrderService.getParkingOrders(status, boy, lot));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getOrdersByUser(@PathVariable("id") Long id){
        return ResponseEntityUtil.responseSuccess(parkingOrderService.getOrdersByUserId(id));
    }

    @GetMapping("{id}")
    public ResponseEntity getOrdersById(@PathVariable("id") Long id){
        return ResponseEntityUtil.responseSuccess(parkingOrderService.getOrderById(id));
    }

    @GetMapping(value = "/fetch",params = "parkingBoyName")
    public ResponseEntity getOrdersByParkingBoyFetch(@RequestParam String parkingBoyName){
        return ResponseEntityUtil.responseSuccess(parkingOrderService.getOrdersByParkingBoy(parkingBoyName,"fetch"));
    }

    @GetMapping(value = "/park",params = "parkingBoyName")
    public ResponseEntity getOrdersByParkingBoyPark(@RequestParam String parkingBoyName){
        return ResponseEntityUtil.responseSuccess(parkingOrderService.getOrdersByParkingBoy(parkingBoyName,"park"));
    }

    @PostMapping
    public ResponseEntity createParkingOrders(@RequestBody AppointmentDto appointmentDto) {
        return ResponseEntityUtil.responseSuccess(parkingOrderService.createParkingOrders(appointmentDto));
    }

    @DeleteMapping
    public ResponseEntity deleteParkingOrder(@RequestParam Long id) {
        parkingOrderService.deleteParkingOrder(id);
        return ResponseEntityUtil.responseSuccess(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateParkingOrder(@RequestBody ParkingOrder parkingOrder){
        return ResponseEntityUtil.responseSuccess(parkingOrderService.updateParkingOrder(parkingOrder));
    }
}


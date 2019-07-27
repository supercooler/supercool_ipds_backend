package com.supercool.supercool_ipds_backend.controller;

import com.supercool.supercool_ipds_backend.common.response.ResponseEntityUtil;
import com.supercool.supercool_ipds_backend.model.ParkingLot;
import com.supercool.supercool_ipds_backend.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity createParkingLot(@RequestBody ParkingLot parkingLot) {
        parkingLotService.createParkingLot(parkingLot);
        return ResponseEntityUtil.responseSuccess(HttpStatus.OK);
    }

    @GetMapping("/current-page/{current}/page-size/{size}")
    public ResponseEntity getParkingLots(@PathVariable int current, @PathVariable int size) {
        return ResponseEntityUtil.responseSuccess(parkingLotService.getParkingLots(current, size));
    }

    @GetMapping("/{name}")
    public ResponseEntity findParkingLotByName(@PathVariable String name) {
        return ResponseEntityUtil.responseSuccess(parkingLotService.findParkingLotByName(name));
    }

    @PutMapping
    public ResponseEntity updateParkingLot (@RequestBody ParkingLot parkingLot) {
        parkingLotService.updateParkingLot(parkingLot);
        return ResponseEntityUtil.responseSuccess(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteParkingLot (@PathVariable Long id) {
        parkingLotService.deleteParkingLotById(id);
        return ResponseEntityUtil.responseSuccess(HttpStatus.OK);
    }

}

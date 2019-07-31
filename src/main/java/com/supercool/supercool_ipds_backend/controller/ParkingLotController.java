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
@CrossOrigin(origins = "*", maxAge = 1000L)
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity createParkingLot(@RequestBody ParkingLot parkingLot) {
        parkingLotService.createParkingLot(parkingLot);
        return ResponseEntityUtil.responseSuccess(HttpStatus.OK);
    }

    @GetMapping("/current-page/{current}/page-size/{pageSize}")
    public ResponseEntity getParkingLots(@PathVariable int current, @PathVariable int pageSize) {
        return ResponseEntityUtil.responseSuccess(parkingLotService.getParkingLots(current, pageSize));
    }

    @GetMapping("/{name}")
    public ResponseEntity findParkingLotByName(@PathVariable String name) {
        return ResponseEntityUtil.responseSuccess(parkingLotService.findParkingLotByName("%"+name+"%"));
    }


    @GetMapping(value = "/boy",params = "parkingBoyName")
    public ResponseEntity getOrdersByParkingBoy(@RequestParam String parkingBoyName){
        return ResponseEntityUtil.responseSuccess(parkingLotService.getParkingLotsByBoy(parkingBoyName));
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

    @GetMapping
    public ResponseEntity getAllParkingLots() {
        return ResponseEntityUtil.responseSuccess(parkingLotService.getAllParkingLots());
    }

    @GetMapping("/management")
    public ResponseEntity getAllManagementParkingLot(){
        return ResponseEntityUtil.responseSuccess(parkingLotService.getAllManagementParkingLots());
    }

}

package com.supercool.supercool_ipds_backend.controller;

import com.supercool.supercool_ipds_backend.common.response.ResponseEntityUtil;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-boys")
@CrossOrigin(origins = "*", maxAge = 1000L)
public class ParkingBoyController {

    @Autowired
    private ParkingBoyService parkingBoyService;

    @GetMapping
    public ResponseEntity getParkingBoys(@RequestParam(value = "name",required = false) String name,
                                         @RequestParam(value="gender",required = false)String gender){
        return ResponseEntityUtil.responseSuccess(parkingBoyService.getParkingBoys(name,gender));
    }

    @PutMapping
    public ResponseEntity updateParkingBoy(@RequestBody ParkingBoy parkingBoy){
        return ResponseEntityUtil.responseSuccess(parkingBoyService.updateParkingBoy(parkingBoy));
    }

    @PostMapping
    public ResponseEntity addParkingBoy(@RequestBody ParkingBoy parkingBoy) {
        return ResponseEntityUtil.responseSuccess(parkingBoyService.addParkingBoy(parkingBoy));
    }
    
}
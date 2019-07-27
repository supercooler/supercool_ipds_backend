package com.supercool.supercool_ipds_backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", maxAge = 1000L)
public class TestController {

    @GetMapping
    public String test () {
        return "Hello Super Cooler!!!";
    }

    @PostMapping
    public String testPost (@RequestParam String param) {
        return param;
    }

    @DeleteMapping
    public String testDelete () {
        return "test delete";
    }

    @PutMapping
    public String testPut() {
        return "test put";
    }
}

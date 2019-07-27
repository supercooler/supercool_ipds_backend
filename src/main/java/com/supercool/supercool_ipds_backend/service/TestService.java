package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.response.ResponseEntityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    /**
     * Prepare the TestService to test global exception catching
     */

    public ResponseEntity testResult() {
        return ResponseEntityUtil.responseSuccess("Hello Super Cooler!!!");
    }

}

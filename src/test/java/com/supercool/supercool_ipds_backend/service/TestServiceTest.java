package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestServiceTest {

    /**
     * Test for ResponseEntity & Controller Advice
     */
    private TestService testService;

    @BeforeEach
    public void setUp() {
        testService = new TestService();
    }

    @Test
    public void should_rerun_responseEntity_when_call_TestService () {
        ResponseEntity responseEntity = testService.testResult();
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }


}

package com.supercool.supercool_ipds_backend.service;


import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void should_return_responseEntity_when_call_TestService_given_customException () {

        TestService testService = mock(TestService.class);

        when(testService.testResult()).thenThrow(new CustomException("0000", "error"));

        Assertions.assertThrows(CustomException.class, ()->{
            testService.testResult();
        });

    }

}

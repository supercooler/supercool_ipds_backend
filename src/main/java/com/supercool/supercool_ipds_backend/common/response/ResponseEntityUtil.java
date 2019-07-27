package com.supercool.supercool_ipds_backend.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtil {

    public static ResponseEntity responseSuccess(Object o) {
        Result<Object> result = new Result<>(o);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    public static ResponseEntity responseCustomException(String code, String message, Object data) {
        Result<Object> result = new Result<>(code);
        result.setMsg(message);
        result.setData(data);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    public static ResponseEntity responseGlobalException(String code, String message) {
        Result<Object> result = new Result<>(code);
        result.setMsg(message);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}

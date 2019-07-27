package com.supercool.supercool_ipds_backend.common.exception;
import com.supercool.supercool_ipds_backend.common.response.ResponseEntityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class SCControllerAdvice {

    /**
     * 全局异常捕捉
     * @param exception: 捕获的异常
     * @param request：http request 请求
     * @return 根据异常类型返回封装好的ResponseEntity
     */

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity exceptionHandler(Exception exception, HttpServletRequest request) {
        ResponseEntity responseEntity;
        if (exception instanceof CustomException) {
            responseEntity = ResponseEntityUtil.responseCustomException(((CustomException) exception).getCode(), exception.getMessage(), ((CustomException) exception).getData());
        } else {
            responseEntity = ResponseEntityUtil.responseGlobalException(ExceptionEnum.GlobalException.getCode(), ExceptionEnum.GlobalException.getMessage());
        }
        return responseEntity;
    }

}

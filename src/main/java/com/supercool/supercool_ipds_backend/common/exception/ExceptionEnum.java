package com.supercool.supercool_ipds_backend.common.exception;

import com.supercool.supercool_ipds_backend.common.constant.Constant;

public enum ExceptionEnum {

    /**
     * 枚举各种异常
     */

    GlobalException(Constant.HTTP_STATUS_ERROR, "服务器出错，请联系管理员。"),
    USER_NOT_EXIST_Exception(Constant.USER_NOT_EXIST,"用户不存在，请联系管理员。"),
    USER_ACCOUNT_ERROR_Exception(Constant.USER_ACCOUNT_ERROR,"用户名与密码错误，请联系管理员。");


    private String code;
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

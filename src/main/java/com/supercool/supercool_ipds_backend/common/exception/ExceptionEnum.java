package com.supercool.supercool_ipds_backend.common.exception;

import com.supercool.supercool_ipds_backend.common.constant.Constant;

public enum ExceptionEnum {

    /**
     * 枚举各种异常
     */

    GlobalException(Constant.HTTP_STATUS_ERROR, "服务器出错，请联系管理员。"),
    User_Not_Exist_Exception(Constant.USER_NOT_EXIST,"用户不存在，请联系管理员。"),
    User_Account_Error_Exception(Constant.USER_ACCOUNT_ERROR,"用户名与密码错误，请联系管理员。"),
    Delete_Not_Found_Exception(Constant.DELETE_NOT_FOUND_EXCEPTION_CODE,"删除对象不存在！");


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

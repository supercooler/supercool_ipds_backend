package com.supercool.supercool_ipds_backend.common.exception;

import com.supercool.supercool_ipds_backend.common.constant.Constant;

public enum ExceptionEnum {

    /**
     * 枚举各种异常
     */

    GlobalException(Constant.HTTP_STATUS_ERROR, "服务器出错，请联系管理员。"),
    User_Not_Exist_Exception(Constant.USER_NOT_EXIST,"用户不存在，请联系管理员。"),
    Register_User_Exist_Exception(Constant.REGISTER_USER_EXIST,"用户名重复！"),
    User_Account_Error_Exception(Constant.USER_ACCOUNT_ERROR,"用户名与密码错误，请联系管理员。"),
    Delete_Not_Found_Exception(Constant.DELETE_NOT_FOUND_EXCEPTION_CODE,"删除对象不存在！"),
    Parking_Boy_Busy_Exception(Constant.DELETE_NOT_FOUND_EXCEPTION_CODE,"停车员正忙！"),
    Update_Not_Fount_Exception(Constant.UPDATE_NOT_FOUND_EXCEPTION_CODE,"更新对象不存在！"),
    Parking_Boy_Not_Exist_Exception(Constant.BOY_NOT_EXIST_CODE,"没有停车员"),
    ALL_PARKING_LOTS_REST_CAPACITY_IS_ZERO(Constant.REST_CAPACITY_ZERO_CODE,"无可用停车场"),
    SHOULD_INPUT_VALID_PARKING_ORDER(Constant.INVALID_PARKING_ORDER,"此订单无效");

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

package com.supercool.supercool_ipds_backend.common.constant;

public class Constant {
    /**
     * 定义系统所需要的常量
     * 命名方式：XX_XX_XX(变量名大写)
     */
    /**
     * https://www.cnblogs.com/zhanghengscnc/p/8824820.html
     * ResultCode 自定义错误状态码
     */

    // Http status
    public static final String HTTP_STATUS_ERROR = "Error";

    //Login Need
    public static final String USER_NOT_EXIST = "20001"; // 用户不存在
    public static final String USER_ACCOUNT_ERROR = "20003"; // 用户名或密码错误

    // manager exception
    public static final String DELETE_NOT_FOUND_EXCEPTION_CODE = "20001";
}

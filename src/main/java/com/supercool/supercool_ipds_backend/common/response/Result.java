package com.supercool.supercool_ipds_backend.common.response;

public class Result<T> {
    private String code;

    private String msg;

    private T data;

    public Result(T data) {
        this.data = data;
    }

    public Result(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

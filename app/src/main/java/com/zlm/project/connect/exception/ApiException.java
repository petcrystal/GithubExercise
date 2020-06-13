package com.zlm.project.connect.exception;

/**
 * @author Milla
 * @create 2019/3/27
 */
public class ApiException extends Exception {

    private int code;
    private String msg;

    // -------------------------------------------
    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    // -------------------------------------------
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // -------------------------------------------
}

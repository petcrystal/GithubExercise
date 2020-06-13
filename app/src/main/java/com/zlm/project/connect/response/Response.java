package com.zlm.project.connect.response;

/**
 * @author Milla
 * @create 2019/3/27
 */
public class Response<T> {

    // -------------------------------------------
    private int ErrorCode;
    private String ErrorMsg;
    private T JData;

    // -------------------------------------------

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public T getData() {
        return JData;
    }

    public void setData(T data) {
        this.JData = data;
    }

    // -------------------------------------------
}

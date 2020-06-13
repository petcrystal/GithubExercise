package com.zlm.project.connect.exception;

import com.google.gson.JsonParseException;

import android.net.ParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author Milla
 * @create 2019/3/27
 */
public class CustomException {

    /**
     * Unknown error.
     */
    public static final int UNKNOWN = 1000;

    /**
     * Parse error.
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * Network error.
     */
    public static final int NETWORK_ERROR = 1002;


    // -------------------------------------------
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(PARSE_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(NETWORK_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            ex = new ApiException(NETWORK_ERROR, e.getMessage());
            return ex;
        } else {
            ex = new ApiException(UNKNOWN, e.getMessage());
            return ex;
        }
    }

    // -------------------------------------------
}

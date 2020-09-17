package com.example.asset_management.connection;
/**
 * Errors
 * <p>
 *     Version 1.0
 * </p>
 * 11.09.20
 * AUTHOR: Dominik Dziersan
 */
public class Errors {

    String value;
    String msg;
    String param;
    String body;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

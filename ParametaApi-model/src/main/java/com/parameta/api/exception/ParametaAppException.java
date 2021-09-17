package com.parameta.api.exception;


public class ParametaAppException extends Exception {


    private int errorCodeEx;
    private int httpStatusEx;
    private String messageCodeEx;

    public ParametaAppException(String msg) {
        super(msg);
    }

    public ParametaAppException(int errorCodeEx, String msg) {
        super(msg);
        this.errorCodeEx = errorCodeEx;
    }

    public ParametaAppException(int errorCodeEx, String msg, String messageCodeEx, int httpStatusEx) {
        super(msg);
        this.errorCodeEx = errorCodeEx;
        this.messageCodeEx = messageCodeEx;
        this.httpStatusEx = httpStatusEx;
    }

    public int getHttpStatusEx() {
        return httpStatusEx;
    }

    public void setHttpStatusEx(int httpStatusEx) {
        this.httpStatusEx = httpStatusEx;
    }

    public String getMessageCodeEx() {
        return messageCodeEx;
    }

    public void setMessageCodeEx(String messageCodeEx) {
        messageCodeEx = messageCodeEx;
    }

    public int getErrorCodeEx() {
        return errorCodeEx;
    }

    public void setErrorCodeEx(int errorCodeEx) {
        this.errorCodeEx = errorCodeEx;
    }
}

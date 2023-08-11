package io.elementor.logic.api.common;

public class HttpStatus {
    private String reason;
    private int code;

    public HttpStatus() {
    }

    public HttpStatus(String reason, int code) {
        this.reason = reason;
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}


package io.elementor.infra;

public class HttpResponseWrapper {
    private int statusCode;
    private String message;
    private String content;

    public HttpResponseWrapper() {
    }

    //region Setters & Getters

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //endregion
}

package io.elementor.logic.api.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.elementor.infra.HttpResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class BaseApiResponse<T> {
    private HttpStatus httpStatus;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private T data;

    public BaseApiResponse(HttpResponseWrapper response){
        this.httpStatus = new HttpStatus(response.getMessage(), response.getStatusCode());
    }

    public BaseApiResponse(Class clz, HttpResponseWrapper response) {
        this.httpStatus = new HttpStatus(response.getMessage(), response.getStatusCode());
        data = (T)deserialize(clz, response.getContent());
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public T getData() {
        return data;
    }

    public String setData(String data) {
        this.data = (T) data;
        return data;
    }

    protected <T> T deserialize(TypeReference<T> type, String json) {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(json, type);
        } catch (IOException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\njson:" + json);
        }
        return t;
    }

    protected <T> T deserialize(Class<T> type, String json) {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(json, type);
        } catch (IOException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return t;
    }

}

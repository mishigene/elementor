package io.elementor.infra.tearDown;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TearDownTaskFileWrapper {
    private Class<? extends TearDownTaskWrapper> aClass;
    private String wrapper;

    public TearDownTaskFileWrapper() {
    }

    public TearDownTaskFileWrapper(TearDownTaskWrapper innerWrapper) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.wrapper = mapper.writeValueAsString(innerWrapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.aClass = innerWrapper.getClass();
    }

    @JsonIgnore
    public Class getMyType() {
        return aClass;
    }

    @JsonIgnore
    public <T extends TearDownTaskWrapper> TearDownTaskWrapper getInnerTask() {
        TearDownTaskWrapper task = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            task = mapper.readValue(this.wrapper, aClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task;
    }

    public Class<? extends TearDownTaskWrapper> getaClass() {
        return aClass;
    }

    public void setaClass(Class<? extends TearDownTaskWrapper> aClass) {
        this.aClass = aClass;
    }

    public String getWrapper() {
        return wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }
}

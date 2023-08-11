
package io.elementor.logic.api.reqresApiEx.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "support"
})
public class UserResponse {

    @JsonProperty("data")

    private Data data;
    @JsonProperty("support")

    private Support support;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserResponse() {
    }

    /**
     * 
     * @param data
     * @param support
     */
    public UserResponse(Data data, Support support) {
        super();
        this.data = data;
        this.support = support;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}

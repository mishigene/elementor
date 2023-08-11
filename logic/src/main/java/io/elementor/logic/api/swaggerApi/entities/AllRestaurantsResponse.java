package io.elementor.logic.api.swaggerApi.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "data"
})
@Getter
@Setter
public class AllRestaurantsResponse {

    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("data")
    public List<Datum> data = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public AllRestaurantsResponse() {

    }

    public AllRestaurantsResponse(Boolean success, List < Datum > data) {
            super();
            this.success = success;
            this.data = data;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties () {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty (String name, Object value){
            this.additionalProperties.put(name, value);
        }

    }

package io.elementor.logic.api.swaggerApi.entities;
import java.util.HashMap;
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
        "name",
        "score",
        "address",
        "id"
})
@Getter
@Setter
public class CreateRestaurantRequest {
    @JsonProperty("name")
    public String name;
    @JsonProperty("score")
    public String score;
    @JsonProperty("address")
    public String address;
    @JsonProperty("id")
    public String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public CreateRestaurantRequest(String name, String score, String address, String id) {
        super();
        this.name = name;
        this.score = score;
        this.address = address;
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

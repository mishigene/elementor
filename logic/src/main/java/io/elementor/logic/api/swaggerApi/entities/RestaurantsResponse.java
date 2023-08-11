package io.elementor.logic.api.swaggerApi.entities;
import lombok.Data;

@Data
public class RestaurantsResponse {
    public boolean success;
    public Datum data = null;
}

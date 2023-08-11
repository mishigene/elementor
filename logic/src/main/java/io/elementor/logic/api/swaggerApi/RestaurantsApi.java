package io.elementor.logic.api.swaggerApi;

import io.elementor.infra.HttpMethod;
import io.elementor.infra.HttpUtil;
import io.elementor.logic.api.common.BaseApiResponse;
import io.elementor.logic.api.swaggerApi.entities.AllRestaurantsResponse;
import io.elementor.logic.api.swaggerApi.entities.CreateRestaurantRequest;
import io.elementor.logic.api.swaggerApi.entities.ResetResponse;
import io.elementor.logic.api.swaggerApi.entities.RestaurantsResponse;

import static io.elementor.infra.Utils.toJson;

public class RestaurantsApi {
    private static final String baseAddress = "https://us-central1-testomate-test.cloudfunctions.net/api";

    public static BaseApiResponse<AllRestaurantsResponse> getAllRestaurants(HttpUtil httpU)
    {
        BaseApiResponse<AllRestaurantsResponse> response = new BaseApiResponse<>(AllRestaurantsResponse.class,httpU.sendRequest(HttpMethod.GET ,baseAddress + "/restaurants" ));
        return response;
    }

    public static BaseApiResponse<ResetResponse> resetRestaurantData(HttpUtil httpU)
    {
        ResetResponse resetResponse = new ResetResponse();
        BaseApiResponse<ResetResponse> response =
                new BaseApiResponse<>(ResetResponse.class,httpU.sendRequest(HttpMethod.POST , baseAddress+"/reset"));
        return response;
    }

    public static BaseApiResponse<AllRestaurantsResponse> CreatedRestaurant (String id , String name , String score , String  address , HttpUtil httpU)
    {
        CreateRestaurantRequest createRestaurantRequest = new CreateRestaurantRequest(name,score,address,id);
        BaseApiResponse<AllRestaurantsResponse> response =
                new BaseApiResponse<>
                        (AllRestaurantsResponse.class,httpU.sendRequest(HttpMethod.POST, baseAddress + "/restaurant" , toJson(createRestaurantRequest)));
        return response;
    }

    public static BaseApiResponse<RestaurantsResponse> updateRestaurant (RestaurantsResponse rest , HttpUtil httpU)
    {
        BaseApiResponse<RestaurantsResponse> response =
                new BaseApiResponse<>
                        (RestaurantsResponse.class,httpU.sendRequest(HttpMethod.PATCH, baseAddress + "/restaurant/"+ rest.getData().getId(), toJson(rest.getData())));
        return response;
    }

    public static BaseApiResponse deleteRestaurant (int id , HttpUtil httpU)
    {
        return new BaseApiResponse<>(httpU.sendRequest(HttpMethod.DELETE, baseAddress + "/restaurant/" + id ));
    }
    public static BaseApiResponse<RestaurantsResponse> getRestaurantById(String id , HttpUtil httpU)
    {
        BaseApiResponse<RestaurantsResponse> response = new BaseApiResponse<>(RestaurantsResponse.class,httpU.sendRequest(HttpMethod.GET ,baseAddress + "/restaurant?id="+id ));
        return response;
    }

}

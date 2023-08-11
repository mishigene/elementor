package io.elementor.logic.api.reqresApiEx;

import io.elementor.infra.HttpMethod;
import io.elementor.infra.HttpUtil;
import io.elementor.logic.api.common.BaseApiResponse;
import io.elementor.logic.api.reqresApiEx.entities.CreateUserRequest.CreateUserRequest;
import io.elementor.logic.api.reqresApiEx.entities.UserResponse;

import java.util.LinkedHashMap;

import static io.elementor.infra.Utils.toJson;

public class ReqResApiUsers {
    private static final String baseAddress = "https://reqres.in/api/users";
    public static BaseApiResponse<UserResponse> getUserById(int id, HttpUtil httpU){
        BaseApiResponse<UserResponse> response =
                new BaseApiResponse<>(UserResponse.class, httpU.sendRequest(HttpMethod.GET, baseAddress + "/" + id));
        System.out.println(response.getData());
        System.out.println(response.getData().getData().getEmail());
        return response;
    }

    public static BaseApiResponse<LinkedHashMap<String, Object>> createUser(String name, String job, HttpUtil httpU){
        CreateUserRequest createUserRequest = new CreateUserRequest(name, job);
        BaseApiResponse<LinkedHashMap<String, Object>> response =
                new BaseApiResponse<>(LinkedHashMap.class, httpU.sendRequest(HttpMethod.POST, baseAddress , toJson(createUserRequest)));
        return response;
    }

    public static BaseApiResponse<LinkedHashMap<String, Object>> updateUser(int id, String name, String job, HttpUtil httpU){
        CreateUserRequest createUserRequest = new CreateUserRequest(name, job);
        BaseApiResponse<LinkedHashMap<String, Object>> response =
                new BaseApiResponse<>(LinkedHashMap.class, httpU.sendRequest(HttpMethod.PUT, baseAddress+"/"+id , toJson(createUserRequest)));
        return response;
    }
}

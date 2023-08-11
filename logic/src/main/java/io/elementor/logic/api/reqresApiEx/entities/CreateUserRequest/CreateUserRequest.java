package io.elementor.logic.api.reqresApiEx.entities.CreateUserRequest;

import lombok.Getter;
import lombok.Setter;

public class CreateUserRequest {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String job;

    public CreateUserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }
}

package io.elementor;

import io.elementor.infra.Utils;

import java.util.Random;

public class UserFactory {

    public static User createUser(){
        User user = new User();
        Random random = new Random();
        user.setId(random.nextInt(999));
        user.setPassword(Utils.randomAlphabeticString(8));
        return user;
    }
}

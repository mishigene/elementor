package io.elementor;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void setup(){
        //Runs only once
/*        if(!StaticDataProvider.IS_INIT){
            System.out.println("Before teat run");
            String file = TestBase.class.getResource("/config/config.json").getFile();
            ConfigProvider.initConfig(Utils.readFromFile(file));
            StaticDataProvider.IS_INIT = true;
        }*/
        //Runs every class
        System.out.println("Before all");

    }

    @AfterAll
    public static void tearDown(){
        System.out.println("After all");
    }
}

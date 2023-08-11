package io.elementor.infra.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static io.elementor.infra.Utils.fromJson;

public class ConfigProvider {
    private static final Logger logger = LoggerFactory.getLogger(ConfigProvider.class);
    private static LinkedHashMap config = new LinkedHashMap();

    public static <T> T getConfig(String key) {
        try {
            return (T) config.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void initConfig(String file) {
        config = fromJson(LinkedHashMap.class, file);
    }
}

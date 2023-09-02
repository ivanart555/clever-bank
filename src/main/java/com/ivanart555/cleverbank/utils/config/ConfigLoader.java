package com.ivanart555.cleverbank.utils.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ConfigLoader {
    private static final Logger logger = LoggerFactory.getLogger(SQLStatements.class);
    private static final String CONFIG_FILE = "/config.yml";
    private static final Map<String, String> configData = loadConfig();

    private static Map<String, String> loadConfig() {
        try (InputStream inputStream = SQLStatements.class.getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                Yaml yaml = new Yaml();
                return yaml.load(inputStream);
            } else {
                logger.error("Failed to load config from  " + CONFIG_FILE + " file! The file may not exist.");
                return null;
            }
        } catch (IOException e) {
            logger.error("Failed to load config from  " + CONFIG_FILE + " file due to an IO error: " + e.getMessage());
            return null;
        }
    }

    public static String getValue(String key) {
        if (configData != null) {
            return String.valueOf(configData.get(key));
        }
        throw new IllegalArgumentException("Configuration value not found for key: " + key);
    }
}
package com.kiriost.game.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by kiriost on 13/04/16.
 */
public class ConfigManager {
    private static ConfigManager instance;
    private final String configFile = "config.properties";
    private Properties properties;
    private InputStream inputStream;

    private ConfigManager() {
        properties = new Properties();

        try {
            inputStream = new FileInputStream(configFile);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Config file '" + configFile + "' was not found.");
            }

            inputStream.close();
        } catch (Exception exc) {
            System.out.println("Config file: " + exc.getMessage());
        }
    }

    public static ConfigManager getInstance() {
        if (instance == null)
            instance = new ConfigManager();
        return instance;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    public int getIntProperty(String name) {
        return Integer.valueOf(properties.getProperty(name));
    }

    public boolean getBooleanProperty(String name) {
        String prop = properties.getProperty(name);
        if (prop.equals("true") || prop.equals("1")) {
            return true;
        }
        return false;
    }
}

package com.rosteringester.db;

import java.io.FileNotFoundException;
import java.util.Map;
import com.rosteringester.configuration.Configuration;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

/**
 * Created by jeshernandez on 6/19/17.
 */
abstract class DbCommonP {
    public Map<String, String> setConfig(String configFile) throws YAMLException, FileNotFoundException {
        Configuration config = new Configuration();
        config.setMap(configFile);
        return config.getMap();
    }

}

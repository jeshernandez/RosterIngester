package com.rosteringester.db;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * Created by jeshernandez on 6/19/17.
 */
abstract class DbCommonP {
    public Map<String, String> setConfig(String configFile) {
        Yaml yaml = new Yaml();
        return (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream(configFile));
    }

}

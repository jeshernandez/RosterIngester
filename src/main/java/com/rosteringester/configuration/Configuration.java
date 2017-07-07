package com.rosteringester.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Michael on 7/6/17.
 */
class Configuration {
    private Map<String, String> map;


    public void setMap(String configFile){
        Yaml yaml = new Yaml();
        this.map = (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream(configFile));
    }

    public Map<String, String> getMap(){
        return map;
    }

}

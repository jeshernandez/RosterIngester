package com.rosteringester.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * Created by Michael on 7/6/17.
 */
public class Configuration {
    private Map<String, String> map;

    /**
     * Setter from a yaml config file.
     * @param configFile
     * @throws FileNotFoundException
     */
    public void setMap(String configFile) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream ios = new FileInputStream(new File(configFile));
        this.map = (Map<String, String>) yaml.load(ios);
    }

    /**
     * Getter for mapped file.
     * @return Map<String, String>
     */
    public Map<String, String> getMap(){
        return map;
    }

}

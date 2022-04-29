package com.prberger3.flexregistry.util;

import java.io.IOException;
import java.util.Properties;

/**
 * This interface contains a default method that can be used anywhere a
 * Properties object is needed to be loaded.
 *
 * @author Eric Knapp
 * @author Paul Berger (January 2022)
 */
public interface PropertiesLoader{

    /**
     * This default method will load a properties file into a Properties
     * instance and return it.
     * @param propertiesFilePath a path to a file on the java classpath list
     * @return a populated Properties instance or an empty Properties instance
     *         if the file path was not found.
     */
    default Properties loadProperties(String propertiesFilePath) throws IOException{

        Properties properties = new Properties();

        properties.load(this.getClass().getResourceAsStream(propertiesFilePath));

        return properties;

    }

}


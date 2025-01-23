package org.project.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {


    private FileInputStream fileReader() throws FileNotFoundException {
        return new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\myData");
    }

    public String readValue(String value) throws IOException {
        Properties properties = new Properties();
        properties.load(fileReader());
        return properties.getProperty(value);
    }

}

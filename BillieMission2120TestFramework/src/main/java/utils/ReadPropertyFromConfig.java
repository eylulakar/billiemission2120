package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFromConfig {

    /**
     * Returns authentication token.
     *
     * @return
     */
    public static String getAuthToken() {
        return getProperty("authentication.token");
    }

    /**
     * Returns base service url.
     *
     * @return
     */
    public static String getServiceUrl() {
        return getProperty("webservice.baseurl");
    }

    /**
     * Reads and returns specified property value from config file.
     *
     * @param propertyName string
     * @return String
     */
    private static String getProperty(final String propertyName) {
        String propertyValue = "";
        try (InputStream input = ReadPropertyFromConfig.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            if (input == null) {
                System.out.println("File not found.");
                return "";
            }
            propertyValue = prop.getProperty(propertyName);

        } catch (IOException ex) {
            System.out.println("Error reading file.");
            ex.printStackTrace();
        }
        return propertyValue;
    }
}

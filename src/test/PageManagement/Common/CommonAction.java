package Common;

import java.io.*;
import java.util.Properties;

public class CommonAction {

    public static String getConfigData(String configDataName) {
        String configValue = System.getProperty(configDataName);
        if (configValue == null) {
            configValue = getPropertyFromPropertiesFile("config", configDataName);
        }
        return configValue;
    }

    public static String getPropertyFromPropertiesFile(String fileName, String propertyName) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        String propertyValue = "";
        try {
            String currentDir = System.getProperty("user.dir");
            String path = currentDir + "/src/test/resources/" + fileName + ".properties";
            if (fileName.equalsIgnoreCase("config")) {
                path = currentDir + "/config.properties";
            }
            inputStream = new FileInputStream(path);
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return propertyValue;
    }

    public static void startSeleniumNode() throws IOException {
        String currentDir = System.getProperty("user.dir");
        ProcessBuilder pb = new ProcessBuilder(currentDir + "/launchNode.sh");
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}

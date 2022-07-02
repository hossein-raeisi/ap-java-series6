package client;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class Config {

    public static String SERVER_ADDRESS;

    public static void loadConfig() {
        Map<String, Object> configMap = readConfig();

        SERVER_ADDRESS = (String) configMap.get("SERVER_ADDRESS");
    }

    public static Map<String, Object> readConfig() {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream("src/main/java/client/config.yaml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Yaml yaml = new Yaml();

        return yaml.load(inputStream);
    }
}

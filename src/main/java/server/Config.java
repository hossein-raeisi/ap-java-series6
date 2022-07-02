package server;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class Config {

    public static int SERVER_PORT;

    public static void loadConfig() {
        Map<String, Object> configMap = readConfig();

        SERVER_PORT = (int) configMap.get("SERVER_PORT");
    }

    public static Map<String, Object> readConfig() {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(new File("src/main/java/server/config.yaml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Yaml yaml = new Yaml();

        return yaml.load(inputStream);
    }
}

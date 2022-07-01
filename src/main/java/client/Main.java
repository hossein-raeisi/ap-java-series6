package client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Main {

    public static void main(String[] args) {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder()
                .uri(URI.create(""))
                .header("", "")
                .build();
    }
}

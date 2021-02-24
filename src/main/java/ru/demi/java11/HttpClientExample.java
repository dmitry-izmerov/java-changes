package ru.demi.java11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

public class HttpClientExample {
    private static final Logger log = LoggerFactory.getLogger(HttpClientExample.class);

    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {
        var client = HttpClient.newHttpClient();
        var req1 = HttpRequest.newBuilder(URI.create("https://www.boredapi.com/api/activity")).build();
        var response = client.send(req1, HttpResponse.BodyHandlers.ofString())
            .body();
        log.info("what to do: {}", response);

        var ssl = SSLContext.getInstance("SSL");
        ssl.init(null, null, null);
        var customClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofMinutes(2))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .sslContext(ssl)
            .build();
        var req2 = HttpRequest
            .newBuilder(URI.create("https://datausa.io/api/data?drilldowns=Nation&measures=Population"))
            .GET()
            .header("Content-Type", "application/json")
            .timeout(Duration.ofMinutes(1))
            .build();
        customClient.sendAsync(req2, HttpResponse.BodyHandlers.ofFile(Path.of("response.json")))
            .thenApply(HttpResponse::body)
            .thenAccept(b -> log.info("response is saved in file: {}", b))
            .join();
    }
}

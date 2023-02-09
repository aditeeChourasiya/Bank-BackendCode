package io.bankbridge.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BanksRemoteCalls {

    private static Map config;

    public static void init() throws Exception {
        config = new ObjectMapper()
                .readValue(Thread.currentThread().getContextClassLoader().getResource("banks-v2.json"), Map.class);
    }

    public String handle(Request request, Response response) throws IOException {

        List<Map> result = new ArrayList<>();
        Set<String> keys = config.keySet();

            //Iterate over the Keys
            for (String key : keys) {

                // Create an object to hold the URL
                URL url = new URL(config.get(key).toString());

                // Open a connection on the URL and cast the response
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Now it's "open", we can set the request method, headers etc.
                connection.setRequestProperty("accept", "application/json");

                // This line makes the request
                InputStream responseStream = connection.getInputStream();

                // Storing the json response body from the InputStream
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> jsonMap = mapper.readValue(responseStream, Map.class);
                result.add(jsonMap);
            }
            String resultAsString = new ObjectMapper().writeValueAsString(result);
            return resultAsString;

    }
}


package com.example.uiagent.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;

@Service
public class PlannerExecutor {

    @Value("${llm.ali.api-key}")
    private String apiKey;

    @Value("${llm.ali.endpoint}")
    private String endpoint;

    private static final int MAX_RETRY = 3;
    private final ObjectMapper mapper = new ObjectMapper();

    public String plan(String prompt) {
        int attempt = 0;
        while (attempt < MAX_RETRY) {
            try {
                URL url = new URL(endpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Authorization", "Bearer " + apiKey);
                conn.setRequestProperty("Content-Type", "application/json");

                String requestBody = "{"
                    + "\"model\": \"qwen-turbo\","
                    + "\"input\": {\"prompt\": \"" + prompt.replace("\"", "\\\"") + "\"},"
                    + "\"parameters\": {\"temperature\": 0.7}"
                    + "}";

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(requestBody.getBytes());
                }

                InputStream response = conn.getInputStream();
                Scanner scanner = new Scanner(response).useDelimiter("\\A");
                String result = scanner.hasNext() ? scanner.next() : "";

                // 尝试解析返回的 JSON 字段（假设为 result.output.text）
                JsonNode root = mapper.readTree(result);
                String content = root.path("output").path("text").asText();
                if (content != null && content.contains("[")) {
                    return content;
                }

            } catch (Exception e) {
                attempt++;
                try {
                    Thread.sleep(500L * attempt); // backoff
                } catch (InterruptedException ignored) {}
            }
        }

        return "[\"SearchInput\", \"FallbackComponent\"]";
    }
}

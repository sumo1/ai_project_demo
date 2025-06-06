
package com.example.uiagent.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class FunctionToolInvoker {

    @Value("${llm.ali.api-key}")
    private String apiKey;

    @Value("${llm.ali.endpoint}")
    private String endpoint;

    private static final int MAX_RETRY = 3;
    private final ObjectMapper mapper = new ObjectMapper();

    public String invoke(String componentType) {
        int attempt = 0;
        while (attempt < MAX_RETRY) {
            try {
                URL url = new URL(endpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Authorization", "Bearer " + apiKey);
                conn.setRequestProperty("Content-Type", "application/json");

                String prompt = "请为组件 " + componentType + " 生成对应 HTML 元素结构";
                String requestBody = "{"
                    + "\"model\": \"qwen-turbo\","
                    + "\"input\": {\"prompt\": \"" + prompt.replace("\"", "\\\"") + "\"},"
                    + "\"parameters\": {\"temperature\": 0.3}"
                    + "}";

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(requestBody.getBytes());
                }

                InputStream response = conn.getInputStream();
                Scanner scanner = new Scanner(response).useDelimiter("\\A");
                String result = scanner.hasNext() ? scanner.next() : "";

                JsonNode root = mapper.readTree(result);
                String html = root.path("output").path("text").asText();
                if (html != null && html.length() > 10) {
                    return html;
                }

            } catch (Exception e) {
                attempt++;
                try {
                    Thread.sleep(300L * attempt);
                } catch (InterruptedException ignored) {}
            }
        }

        return "<div>未知组件：" + componentType + "</div>";
    }
}

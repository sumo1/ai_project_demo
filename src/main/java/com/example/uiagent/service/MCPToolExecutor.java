
package com.example.uiagent.service;

import java.util.Map;

public class MCPToolExecutor {

    private final MCPClient client = new MCPClient();

    public String generateAddressSelector(String prompt) {
        Map<String, Object> data = client.call(prompt);
        // 简化渲染为 HTML
        return "<select><option>北京</option></select>";
    }
}

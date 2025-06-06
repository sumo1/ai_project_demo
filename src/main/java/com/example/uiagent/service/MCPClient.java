
package com.example.uiagent.service;

import java.util.Map;

public class MCPClient {
    public Map<String, Object> call(String prompt) {
        // 模拟 MCP 地址服务响应
        return Map.of(
            "province", new String[]{"北京", "上海"},
            "city", Map.of("北京", new String[]{"北京市"}),
            "district", Map.of("北京市", new String[]{"海淀区"}),
            "street", Map.of("海淀区", new String[]{"中关村街道"})
        );
    }
}

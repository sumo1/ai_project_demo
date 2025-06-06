
package com.example.uiagent.service;

public class ModelRouter {
    public String route(String taskType) {
        return switch (taskType) {
            case "PLANNING" -> "gpt-4";
            case "FUNCTION_CALL" -> "qwen-7b";
            case "MCP_TOOL_CALL" -> "open-mcp-ui";
            default -> "qwen-default";
        };
    }
}

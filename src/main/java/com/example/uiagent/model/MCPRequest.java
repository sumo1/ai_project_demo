package com.example.uiagent.model;

import java.util.Map;

public class MCPRequest {
    private String prompt;
    private Map<String, Object> context;
    private Map<String, Object> params;

    // Getters and Setters
    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public Map<String, Object> getContext() { return context; }
    public void setContext(Map<String, Object> context) { this.context = context; }

    public Map<String, Object> getParams() { return params; }
    public void setParams(Map<String, Object> params) { this.params = params; }
}

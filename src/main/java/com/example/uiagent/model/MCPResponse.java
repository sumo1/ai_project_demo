
package com.example.uiagent.model;

import java.util.Map;

public class MCPResponse {
    private String result;
    private Map<String, Object> metadata;

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
}

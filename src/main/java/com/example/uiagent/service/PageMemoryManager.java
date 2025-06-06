
package com.example.uiagent.service;

import java.util.HashMap;
import java.util.Map;

public class PageMemoryManager {
    private final Map<String, String> memory = new HashMap<>();

    public void save(String sessionId, String structureJson) {
        memory.put(sessionId, structureJson);
    }

    public String recall(String sessionId) {
        return memory.getOrDefault(sessionId, "");
    }
}

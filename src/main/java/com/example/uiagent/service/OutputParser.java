
package com.example.uiagent.service;

import com.example.uiagent.model.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OutputParser {
    private final ObjectMapper mapper = new ObjectMapper();

    public Component parse(String output) {
        try {
            return mapper.readValue(output, Component.class);
        } catch (Exception e) {
            Component fallback = new Component();
            fallback.setType("Unknown");
            fallback.setRaw(output);
            return fallback;
        }
    }
}

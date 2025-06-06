
package com.example.uiagent.controller;

import com.example.uiagent.service.ChatFlowService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/page")
public class PageController {

    private final ChatFlowService flowService = new ChatFlowService();

    @PostMapping("/render")
    public Map<String, Object> render(@RequestBody Map<String, String> input) {
        String prompt = input.getOrDefault("prompt", "生成一个包含地址选择器的页面");
        String html = flowService.generatePage(prompt);
        return Map.of(
            "result", html,
            "model", "mixed",
            "latency", 100
        );
    }
}

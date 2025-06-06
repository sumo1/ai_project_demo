
package com.example.uiagent.service;

import java.util.ArrayList;
import java.util.List;

public class ChatFlowService {

    private final PlannerExecutor planner = new PlannerExecutor();
    private final PromptTemplate promptBuilder = new PromptTemplate();
    private final FunctionToolInvoker toolInvoker = new FunctionToolInvoker();
    private final MCPToolExecutor mcpTool = new MCPToolExecutor();
    private final PageMemoryManager memoryManager = new PageMemoryManager();
    private final ReActProcessor react = new ReActProcessor();

    public String generatePage(String userPrompt) {
        String planJson = planner.plan(userPrompt);
        List<String> components = List.of("SearchInput", "AddressSelector", "ResultList");
        StringBuilder html = new StringBuilder();
        for (String c : components) {
            if (c.equals("AddressSelector")) {
                html.append(mcpTool.generateAddressSelector(userPrompt));
            } else {
                html.append(toolInvoker.invoke(c));
            }
        }
        memoryManager.save("session-001", planJson);
        return html.toString();
    }

    public String refinePage(String sessionId, String userInput) {
        String context = memoryManager.recall(sessionId);
        return react.respond(userInput, context);
    }
}

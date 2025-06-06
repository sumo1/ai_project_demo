
package com.example.uiagent.service;

import java.util.List;

public class PromptTemplate {

    public String renderForPlan(String userPrompt, String history) {
        return "用户需求：" + userPrompt + "\n已生成组件：" + history + "\n请用 JSON 格式返回组件计划，如：[\"SearchInput\", \"AddressSelector\"]";
    }

    public String renderForTool(String componentType) {
        return "请为组件类型 " + componentType + " 生成对应的 HTML 结构或 JSON Schema";
    }
}

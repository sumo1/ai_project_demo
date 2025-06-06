
package com.example.uiagent.service;

public class ReActProcessor {

    public String respond(String userIntent, String context) {
        // 模拟 ReAct 推理方式返回结构调整
        if (userIntent.contains("加一个分页")) {
            return "<div>分页组件</div>";
        }
        return "";
    }
}

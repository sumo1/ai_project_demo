
package com.example.uiagent.controller;

import com.example.uiagent.model.EvalEntity;
import com.example.uiagent.repository.EvalRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/eval")
public class EvalController {

    private final EvalRepository evalRepository;

    public EvalController(EvalRepository evalRepository) {
        this.evalRepository = evalRepository;
    }

    @PostMapping("/submit")
    public EvalEntity submit(@RequestBody EvalEntity eval) {
        eval.setTimestamp(Instant.now());
        return evalRepository.save(eval);
    }
}

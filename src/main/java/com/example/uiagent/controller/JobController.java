
package com.example.uiagent.controller;

import com.example.uiagent.model.JobEntity;
import com.example.uiagent.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/{sessionId}")
    public List<JobEntity> getJobsBySession(@PathVariable String sessionId) {
        return jobRepository.findBySessionId(sessionId);
    }
}

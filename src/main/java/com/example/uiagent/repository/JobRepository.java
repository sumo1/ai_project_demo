
package com.example.uiagent.repository;

import com.example.uiagent.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
    List<JobEntity> findBySessionId(String sessionId);
}

package com.engineeringwithramaa.TaskCache.repository;

import com.engineeringwithramaa.TaskCache.model.Task;
import com.oracle.coherence.spring.data.config.CoherenceMap;
import com.oracle.coherence.spring.data.repository.CoherenceRepository;

@CoherenceMap("tasks-cache")
public interface SpringDataTaskRepository extends CoherenceRepository<Task, String> {
}

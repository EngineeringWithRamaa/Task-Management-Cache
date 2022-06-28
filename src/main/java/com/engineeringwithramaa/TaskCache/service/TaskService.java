package com.engineeringwithramaa.TaskCache.service;

import com.engineeringwithramaa.TaskCache.model.Task;

import java.util.Collection;

public interface TaskService {
    Collection<Task> findAll(boolean completed);
	Task find(String id);
	void save(Task task);
	void removeById(String id);
	void deleteCompletedTasks();
	Collection<Task> deleteCompletedTasksAndReturnRemainingTasks();
	Task update(String id, Task task);
}

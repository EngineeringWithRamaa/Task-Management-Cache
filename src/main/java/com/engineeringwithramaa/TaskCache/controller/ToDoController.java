package com.engineeringwithramaa.TaskCache.controller;

import com.engineeringwithramaa.TaskCache.model.Task;
import com.engineeringwithramaa.TaskCache.service.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/tasks")
public class ToDoController {
    private TaskService taskService;

    public ToDoController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Collection<Task> getTasks(@RequestParam(defaultValue = "false") boolean completed) {
        return taskService.findAll(completed);
    }

    @PostMapping
    public void createTask(@RequestBody Task task) {
        taskService.save(new Task(task.getDescription()));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.removeById(id);
    }

    @DeleteMapping
    public void deleteCompletedTasks() {
        taskService.deleteCompletedTasks();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task task) {
        return taskService.update(id, task);
    }
}

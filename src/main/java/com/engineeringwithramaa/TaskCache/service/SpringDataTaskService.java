    package com.engineeringwithramaa.TaskCache.service;

    import com.engineeringwithramaa.TaskCache.exception.TaskNotFoundException;
    import com.engineeringwithramaa.TaskCache.model.Task;
    import com.engineeringwithramaa.TaskCache.repository.SpringDataTaskRepository;
    import com.tangosol.util.Filter;
    import com.tangosol.util.Filters;
    import org.springframework.stereotype.Service;
    import org.springframework.util.Assert;

    import java.util.Collection;

    @Service
    public class SpringDataTaskService implements TaskService{

        private static final String TASK_NOT_FOUND_MESSAGE = "Unable to find task with id '%s'.";

        private final SpringDataTaskRepository taskRepository;

        public SpringDataTaskService(SpringDataTaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }
        @Override
        public Collection<Task> findAll(boolean completed) {
            final Filter<Task> filter = !completed
                    ? Filters.always()
                    : Filters.equal(Task::getCompleted, true);
            return taskRepository.getAllOrderedBy(filter, Task::getCreatedAt);
        }

        @Override
        public Task find(String id) throws TaskNotFoundException {
           Assert.hasText(id, "The Task Id must not be null or empty.");
            final Task task = taskRepository.findById(id).orElseThrow(() ->
                    new TaskNotFoundException(String.format(TASK_NOT_FOUND_MESSAGE, id)));
            return task;
        }

        @Override
        public void save(Task task) {
            Assert.notNull(task, "The task must not be null.");
            taskRepository.save(task);
        }

        @Override
        public void removeById(String id) {
            Assert.hasText(id, "The Task Id must not be null or empty.");
            taskRepository.deleteById(id);
        }

        @Override
        public void deleteCompletedTasks() {
            taskRepository.deleteAll(Filters.equal(Task::getCompleted, true), false);
        }

        @Override
        public Collection<Task> deleteCompletedTasksAndReturnRemainingTasks() {
            this.deleteCompletedTasks();
            return this.findAll(false);
        }

        @Override
        public Task update(String id, Task task) {
            Assert.hasText(id, "The Task Id must not be null or empty.");
            Assert.notNull(task, "The Task must not be null.");

            final String description = task.getDescription();
            final Boolean completed = task.getCompleted();

            try {
                return this.taskRepository.update(id, tsk -> {
                    if (description != null) {
                        tsk.setDescription(description);
                    }
                    if (completed != null) {
                        tsk.setCompleted(completed);
                    }
                    return tsk;
                });
            }
            catch (Exception ex) {
                throw new TaskNotFoundException(String.format(TASK_NOT_FOUND_MESSAGE, id));
            }
        }
    }

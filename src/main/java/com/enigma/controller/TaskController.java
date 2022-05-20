package com.enigma.controller;

import com.enigma.dto.*;
import com.enigma.entity.Task;
import com.enigma.exceptions.TaskNotFoundException;
import com.enigma.exceptions.UserNotFoundException;
import com.enigma.repository.TaskRepository;
import com.enigma.service.TaskService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private TaskService taskService;
    private TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody TaskAddDTO taskAddDTO) {
        return taskService.addTask(taskAddDTO);
    }

    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) throws TaskNotFoundException {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTask(@PathVariable("taskId") Long taskId) throws TaskNotFoundException {
        return taskService.getTask(taskId);
    }

    @PatchMapping("/tasks/{taskId}/{userId}")
    public Task assignUserToTask(@PathVariable("taskId") Long taskId, @PathVariable("userId") Long userId) throws TaskNotFoundException, UserNotFoundException {
        return taskService.assignUserToTask(taskId, userId);
    }

    @DeleteMapping("/tasks/{taskId}/{userId}")
    public void deleteUserFromTask(@PathVariable("taskId") Long taskId, @PathVariable("userId") Long userId) throws UserNotFoundException, TaskNotFoundException {
        taskService.deleteUserFromTask(taskId, userId);
    }

    @PatchMapping("/tasks/{taskId}/status")
    public Task changeTaskStatus(@PathVariable("taskId") Long taskId, @RequestBody TaskStatusDTO taskStatusDTO) throws TaskNotFoundException {
        return taskService.changeTaskStatus(taskId, taskStatusDTO);
    }

    @PatchMapping("/tasks/{taskId}/title")
    public Task changeTaskTitle(@PathVariable("taskId") Long taskId, @RequestBody TaskTitleDTO taskTitleDTO) throws TaskNotFoundException {
        return taskService.changeTaskTitle(taskId, taskTitleDTO);
    }

    @PatchMapping("/tasks/{taskId}/description")
    public Task changeTaskDescription(@PathVariable("taskId") Long taskId, @RequestBody TaskDescDTO taskDescDTO) throws TaskNotFoundException {
        return taskService.changeTaskDescription(taskId, taskDescDTO);
    }

    @PatchMapping("/tasks/{taskId}/deadline")
    public Task changeTaskDeadline(@PathVariable("taskId") Long taskId, @RequestBody TaskDeadlineDTO taskDeadlineDTO) throws TaskNotFoundException {
        return taskService.changeTaskDeadline(taskId, taskDeadlineDTO);
    }

    // Example usage: http://localhost:8080/api/tasks?title=abc
    @GetMapping(value = "/tasks", params = {"title"})
    public Iterable<Task> findByTitle(
            @Spec(path = "title", params = "title", spec = Like.class) Specification<Task> spec,
            Pageable pageable) {
        return taskRepository.findAll(spec, pageable);
    }

    @GetMapping(value = "/tasks", params = {"description"})
    public Iterable<Task> findByDescription(
            @Spec(path = "description", params = "description", spec = Like.class) Specification<Task> spec,
            Pageable pageable) {
        return taskRepository.findAll(spec, pageable);
    }

    // Example usage: http://localhost:8080/api/tasks?before=2020-05-25
    @GetMapping(value = "/tasks", params = {"before"})
    public Iterable<Task> findByDeadline(
            @Spec(path = "deadline", params = "before", spec = LessThanOrEqual.class) Specification<Task> spec,
            Pageable pageable) {
        return taskRepository.findAll(spec, pageable);
    }

    @GetMapping(value = "/tasks", params = {"status"})
    public Iterable<Task> findByStatus(
            @Spec(path = "status", params = "status", spec = Equal.class) Specification<Task> spec,
            Pageable pageable) {
        return taskRepository.findAll(spec, pageable);
    }

}








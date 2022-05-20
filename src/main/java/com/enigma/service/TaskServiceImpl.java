package com.enigma.service;

import com.enigma.dto.*;
import com.enigma.entity.Task;
import com.enigma.entity.User;
import com.enigma.enums.TaskStatus;
import com.enigma.exceptions.TaskNotFoundException;
import com.enigma.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import com.enigma.repository.TaskRepository;
import com.enigma.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {


    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Task addTask(TaskAddDTO taskAddDTO) {
        Task task = new Task();
        task.setTitle(taskAddDTO.getTitle());
        task.setDescription(taskAddDTO.getDescription());
        task.setStatus(TaskStatus.NEW);
        task.setDeadline(taskAddDTO.getDeadline());
        task.setAssignedUsers(new ArrayList<>());
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task getTask(Long taskId) throws TaskNotFoundException {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Requested task does not exist"));
    }

    @Override
    public void deleteTask(Long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Requested task does not exist"));
        task.getAssignedUsers().forEach(u -> u.setTask(null));
        taskRepository.delete(task);
    }

    @Override
    public Task assignUserToTask(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Requested task does not exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested user does not exist"));
        task.addUser(user);
        return task;
    }

    @Override
    public void deleteUserFromTask(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Requested task does not exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested user does not exist"));
        user.setTask(null);
    }

    @Override
    public Task changeTaskStatus(Long taskId, TaskStatusDTO taskStatusDTO) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Requested task does not exist"));
        task.setStatus(taskStatusDTO.getStatus());
        return task;
    }

    @Override
    public Task changeTaskTitle(Long taskId, TaskTitleDTO taskTitleDTO) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Requested task does not exist"));
        task.setTitle(taskTitleDTO.getTitle());
        return task;
    }

    @Override
    public Task changeTaskDescription(Long taskId, TaskDescDTO taskDescDTO) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Requested task does not exist"));
        task.setDescription(taskDescDTO.getDescription());
        return task;
    }

    @Override
    public Task changeTaskDeadline(Long taskId, TaskDeadlineDTO taskDeadlineDTO) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Requested task does not exist"));
        task.setDeadline(taskDeadlineDTO.getDeadline());
        return task;
    }
}































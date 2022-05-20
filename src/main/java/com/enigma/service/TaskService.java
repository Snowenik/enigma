package com.enigma.service;

import com.enigma.dto.*;
import com.enigma.entity.Task;
import com.enigma.exceptions.TaskNotFoundException;
import com.enigma.exceptions.UserNotFoundException;

public interface TaskService {

    Task addTask(TaskAddDTO taskAddDTO);
    Task getTask(Long taskId) throws TaskNotFoundException;
    void deleteTask(Long taskId) throws TaskNotFoundException;
    Task assignUserToTask(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException;
    void deleteUserFromTask(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException;
    Task changeTaskStatus(Long taskId,TaskStatusDTO taskStatusDTO) throws TaskNotFoundException;
    Task changeTaskTitle(Long taskId, TaskTitleDTO taskTitleDTO) throws TaskNotFoundException;
    Task changeTaskDescription(Long taskId, TaskDescDTO taskDescDTO) throws TaskNotFoundException;
    Task changeTaskDeadline(Long taskId, TaskDeadlineDTO taskDeadlineDTO) throws TaskNotFoundException;

}

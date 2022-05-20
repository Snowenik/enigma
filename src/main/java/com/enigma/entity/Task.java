package com.enigma.entity;

import com.enigma.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    private List<User> assignedUsers;


    public Task() {}

    public void addUser(User user) {
        this.assignedUsers.add(user);
        user.setTask(this);
    }

    public void removeUser(User user) {
        this.assignedUsers.remove(user);
        user.setTask(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }
}

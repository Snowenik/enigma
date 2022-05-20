package com.enigma.dto;

import java.util.Date;

public class TaskAddDTO {

    private String title;
    private String description;
    private Date deadline;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public Date getDeadline() {
        return deadline;
    }
}

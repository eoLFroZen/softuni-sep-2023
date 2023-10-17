package com.plannerapp.model.dto.task;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.enums.PriorityName;

public class TaskDTO {

    private Long id;

    private String description;

    private String dueDate;

    private PriorityName priority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public void setPriority(PriorityName priority) {
        this.priority = priority;
    }

    public static TaskDTO createFromTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority().getName());
        taskDTO.setDueDate(task.getDueDate().toString());

        return taskDTO;
    }
}

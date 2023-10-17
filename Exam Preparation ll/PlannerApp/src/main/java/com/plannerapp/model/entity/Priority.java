package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private PriorityName name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<Task> tasks;

    public PriorityName getName() {
        return name;
    }

    public void setName(PriorityName name) {
        this.name = name;
        setDescription(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    private void setDescription(PriorityName name) {
        String description = "";

        switch (name) {
            case URGENT -> description = "An urgent problem that blocks the system use until the issue is resolved.";
            case IMPORTANT ->
                    description = "A core functionality that your product is explicitly supposed to perform is compromised.";
            case LOW -> description = "Should be fixed if time permits but can be postponed.";
        }

        this.description = description;
    }
}

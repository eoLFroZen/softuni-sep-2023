package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ConditionName name;

    @NotNull
    private String description;

    public ConditionName getName() {
        return name;
    }

    public void setName(ConditionName name) {
        this.name = name;
        this.setDescription(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void setDescription(ConditionName name) {
        String description = "";

        switch (name) {
            case EXCELLENT -> description = "In perfect condition";
            case GOOD -> description = "Some signs of wear and tear or minor defects";
            case ACCEPTABLE -> description = "The item is fairly worn but continues to function properly";
        }

        this.description = description;
    }
}

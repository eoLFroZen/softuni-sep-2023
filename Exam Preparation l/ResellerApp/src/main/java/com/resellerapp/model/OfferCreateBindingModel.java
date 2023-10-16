package com.resellerapp.model;

import com.resellerapp.model.enums.ConditionName;

import java.math.BigDecimal;

public class OfferCreateBindingModel {

    private String description;
    private BigDecimal price;
    private ConditionName condition;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ConditionName getCondition() {
        return condition;
    }

    public void setCondition(ConditionName condition) {
        this.condition = condition;
    }
}

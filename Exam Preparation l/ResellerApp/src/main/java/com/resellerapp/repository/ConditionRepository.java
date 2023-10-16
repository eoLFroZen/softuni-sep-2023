package com.resellerapp.repository;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, UUID> {
    Condition findByName(ConditionName condition);
}

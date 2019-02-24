package com.kastech.repository;

import com.kastech.model.ActivityTable;
import org.springframework.data.repository.CrudRepository;

public interface TestCaseStepRepository extends CrudRepository<ActivityTable, Integer> {
}


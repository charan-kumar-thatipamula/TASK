package com.kastech.repository;

import com.kastech.model.TestCycleCaseDefnTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCycleRepository extends CrudRepository<TestCycleCaseDefnTable, String> {
    List<TestCycleCaseDefnTable> findByTestCycleId(String testCycleId);
}

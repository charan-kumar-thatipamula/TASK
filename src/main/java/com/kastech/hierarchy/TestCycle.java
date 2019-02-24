package com.kastech.hierarchy;

import com.kastech.model.TestCycleCaseDefnTable;
import com.kastech.repository.TestCaseRepository;
import com.kastech.repository.TestCycleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class TestCycle extends Entity {

    TestCycle(String id) {
        this.id = id;
        this.subEntities = new ArrayList<>();
        this.runSubEntitiesParellel = true;
        logger = Logger.getLogger(TestCycle.class.getName());
    }

    @Override
    void runComplete() {

    }

    @Override
    Entity createSubEntity(String id) {
        return new TestCase(id);
    }

    @Override
    List<String> getSubEntityIds() {
        TestCycleRepository testCycleRepository = this.getBeanFactory().getTestCycleRepository();
        List<TestCycleCaseDefnTable> testCycleList = testCycleRepository.findByTestCycleId(getId());
        List<String> testCaseIds = new ArrayList<>();
        for (TestCycleCaseDefnTable testCycleTable : testCycleList) {
            testCaseIds.add(testCycleTable.getTestCaseId());
        }
        return testCaseIds;
    }
}

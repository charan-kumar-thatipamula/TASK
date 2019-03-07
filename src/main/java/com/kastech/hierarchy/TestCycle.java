package com.kastech.hierarchy;

import com.kastech.config.ApplicationContextProvider;
import com.kastech.model.TestCycleCaseDefnTable;
import com.kastech.repository.TestCaseRepository;
import com.kastech.repository.TestCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@Scope("prototype")
class TestCycle extends Entity {

    @Autowired
    ApplicationContextProvider applicationContextProvider;

//    TestCycle(String id) {
//        this.id = id;
//        this.subEntities = new ArrayList<>();
//        this.runSubEntitiesParellel = true;
//        logger = Logger.getLogger(TestCycle.class.getName());
//    }

    public void initialize(String id) {
        this.id = id;
        this.subEntities = new ArrayList<>();
        this.runSubEntitiesParellel = true;
        logger = Logger.getLogger(TestCycle.class.getName());
    }

    @Override
    void hookAfterRun() {

    }

    @Override
    Entity createSubEntity(String id) {
//        return new TestCase(id);
        TestCase testCase = applicationContextProvider.getContext().getBean(TestCase.class);
        testCase.initialize(id);
        return testCase;
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

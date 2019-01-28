package com.kastech.hierarchy;

import com.kastech.model.TestCaseDetailsTable;
import com.kastech.repository.TestCaseRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

class TestCase extends Entity {

    TestCase(String id) {
        this.id = id;
        this.subEntities = new LinkedList<>();
        this.runSubEntitiesParellel = false;
        logger = Logger.getLogger(TestCase.class.getName());
    }

    @Override
    void runComplete() {

    }

    @Override
    Entity createSubEntity(String id) {
        return new TestCaseStep(id);
    }

    @Override
    List<String> getSubEntityIds() {
        TestCaseRepository testCaseRepository = this.getBeanFactory().getTestCaseRepository();
        List<TestCaseDetailsTable> testCaseDetailsTables = testCaseRepository.findByTestCaseIdOrderByCreateAtAsc(getId());
        List<String> testCaseSteps = new LinkedList<>();
        for (TestCaseDetailsTable testCaseDetailsTable : testCaseDetailsTables) {
            testCaseSteps.add(getTestCase(testCaseDetailsTable));
        }
        return testCaseSteps;
    }

    private String getTestCase(TestCaseDetailsTable t) {
        List<String> testCase = new LinkedList<>();
        // TODO: modify below order based on requirement
        addToTestCase(t.getKeyword(), testCase);
        addToTestCase(t.getKeyInData(), testCase);
        addToTestCase(t.getObjectId(), testCase);
        addToTestCase(t.getScreenShot(), testCase);
        return String.join(";", testCase);
    }

    private void addToTestCase(String s, List<String> testCase) {
        if (s != null && s.length() !=0) {
            testCase.add(s);
        }
    }
}

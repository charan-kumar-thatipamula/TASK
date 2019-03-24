package com.kastech.service;

import com.kastech.hierarchy.Activity;
import com.kastech.hierarchy.Run;
import com.kastech.hierarchy.TestCase;
import com.kastech.hierarchy.TestCycle;
import com.kastech.hierarchy.enums.TestType;
import com.kastech.model.TestCaseStatus;
import com.kastech.repository.TestCaseStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TestCaseStatusService {
    Logger logger = Logger.getLogger(TestCaseStatusService.class.getName());
    @Autowired
    TestCaseStatusRepository testCaseStatusRepository;
    public void updateStatus(TestCase testCase, String status) {
        try {
            TestCycle testCycle = (TestCycle) testCase.getSuperEntity();
            Activity activity = (Activity) testCycle.getSuperEntity();
            Run run = (Run) activity.getSuperEntity();
            String testType = TestType.Automation.getTestType();
            List<TestCaseStatus> testCaseStatusList = testCaseStatusRepository.getEntry(run.getId(),
                    activity.getId(),
                    testCycle.getId(),
                    testCase.getId(),
                    testType);
            if (testCaseStatusList != null && !testCaseStatusList.isEmpty()) {
                TestCaseStatus testCaseStatus = testCaseStatusList.get(0);
                testCaseStatus.setStatus(status);
                testCaseStatusRepository.save(testCaseStatus);
                logger.log(Level.INFO, "updated status for RunId: " + run.getId() + "; ActivityId: " + activity.getId() + "; TestCycleId: " + testCycle.getId() + "; TestCaseId" + testCase.getId());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception updating status: " + e.getMessage());
        }
    }
}

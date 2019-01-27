package com.kastech.service;

import com.kastech.repository.ActivityRepository;
import com.kastech.repository.TestCaseRepository;
import com.kastech.repository.TestCaseStepRepository;
import com.kastech.repository.TestCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanFactory {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    TestCycleRepository testCycleRepository;
    @Autowired
    TestCaseRepository testCaseRepository;
    @Autowired
    TestCaseStepRepository testCaseStepRepository;

    public ActivityRepository getActivityRepository() {
        return activityRepository;
    }

    public TestCycleRepository getTestCycleRepository() {
        return testCycleRepository;
    }

    public TestCaseRepository getTestCaseRepository() {
        return testCaseRepository;
    }

    public TestCaseStepRepository getTestCaseStepRepository() {
        return testCaseStepRepository;
    }
}

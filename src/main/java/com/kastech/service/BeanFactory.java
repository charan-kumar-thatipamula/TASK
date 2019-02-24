package com.kastech.service;

import com.kastech.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
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
    @Autowired
    RunRepository runRepository;
    @Autowired
    TaskExecutor taskExecutor;

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

    public RunRepository getRunRepository() {
        return runRepository;
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }
}

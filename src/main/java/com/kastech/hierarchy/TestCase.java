package com.kastech.hierarchy;

import com.kastech.config.ApplicationContextProvider;
import com.kastech.model.TestCaseDetailsTable;
import com.kastech.repository.TestCaseRepository;
import com.kastech.service.TestCaseStatusService;
import com.kastech.supportlibraries.ExecuteScripts;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@Component
@Scope("prototype")
public class TestCase extends Entity {
    @Autowired
    ApplicationContextProvider applicationContextProvider;

    @Autowired
    ExecuteScripts driverObject;

    @Autowired
    TestCaseRepository testCaseRepository;
    @Autowired
    TestCaseStatusService testCaseStatusService;

    WebDriver driver = null;
    String resultsFolder = null;
    String browserFolder = null;
    String browser = null;
    String module = null;
    String homePath = null;
    List<TestCaseDetailsTable> testCaseDetailsTableEntries = null;
    String status;
//    TestCase(String id) {
//        this.id = id;
//        this.subEntities = new LinkedList<>();
//        this.runSubEntitiesParellel = false;
//        logger = Logger.getLogger(TestCase.class.getName());
//    }

    public void initialize(String id) {
        this.id = id;
        this.subEntities = new LinkedList<>();
        this.runSubEntitiesParellel = false;
        logger = Logger.getLogger(TestCase.class.getName());
        status = "Running";
    }

    @Override
    public void hookBeforeRun() {
        // TODO: read browser details rom Config.properties file or from db
        String port = "";
        browser = "Chrome";
        testCaseStatusService.updateStatus(this, status);
        driver = driverObject.SelectDriver(browser,port, this);
        driver.manage().window().maximize();
        try {
            homePath = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultsFolder = driverObject.CreateExecutionFolder(homePath);
        String scenarioFolder = driverObject.CreateScenarioFolder(resultsFolder, module);
        browserFolder = driverObject.CreateBrowserFolder(scenarioFolder, browser);
    }

    void runSubEntitiesInSequence () {
        try {
            driverObject.runTestCase(testCaseDetailsTableEntries, module, driver, homePath, browserFolder);
            status = "Success";
        } catch (Exception e) {
            status = "Failed";
            this.getLogger().log(Level.SEVERE, "Exception while running the test case", e.getMessage());
        }
    }

    @Override
    void hookAfterRun() {
        driver.manage().deleteAllCookies();
        testCaseStatusService.updateStatus(this, status);
        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
            driver.close();
            driver.quit();
        }
    }

    @Override
    Entity createSubEntity(String id) {
//        return new TestCaseStep(id);
        TestCaseStep testCaseStep = applicationContextProvider.getContext().getBean(TestCaseStep.class);
        testCaseStep.initialize(id);
        testCaseStep.setSuperEntity(this);
        return testCaseStep;
    }

    @Override
    List<String> getSubEntityIds() {
        TestCaseRepository testCaseRepository = this.getBeanFactory().getTestCaseRepository();
        testCaseDetailsTableEntries = testCaseRepository.findByTestCaseIdOrderById(getId());
        if (testCaseDetailsTableEntries != null && !testCaseDetailsTableEntries.isEmpty()) {
            module = testCaseDetailsTableEntries.get(0).getTestCaseId();
        }
        List<String> testCaseSteps = new LinkedList<>();
        for (TestCaseDetailsTable testCaseDetailsTable : testCaseDetailsTableEntries) {
            testCaseSteps.add(getTestCaseStep(testCaseDetailsTable));
        }
        return testCaseSteps;
    }

    private String getTestCaseStep(TestCaseDetailsTable t) {
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

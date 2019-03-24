package com.kastech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test_cycle_run")
public class RunTable {

    @Id
    int id;

    @Column(name="run_id")
    String runId;

    @Column(name="user_id")
    String userId;

    @Column(name="activity_id")
    String activityId;

    @Column(name = "test_cycle_id")
    String testCycleId;

//    String browser;
//    String username;
//    String password;
    String proxy;
    String tenant;
    String mode;
    @Column(name = "run_dttm")
    String runDateTime;

    @Column(name = "report_id")
    String reportId;

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//    public String getBrowser() {
//        return browser;
//    }
//
//    public void setBrowser(String browser) {
//        this.browser = browser;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTestCycleId() {
        return testCycleId;
    }

    public void setTestCycleId(String testCycleId) {
        this.testCycleId = testCycleId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRunDateTime() {
        return runDateTime;
    }

    public void setRunDateTime(String runDateTime) {
        this.runDateTime = runDateTime;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
}


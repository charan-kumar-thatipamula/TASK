package com.kastech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_cycle_case_defn")
public class TestCycleCaseDefnTable {

    @Id
    int id;

    @Column(name = "test_cycle_id")
    String testCycleId;

    @Column(name = "test_case_id")
    String testCaseId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestCycleId() {
        return testCycleId;
    }

    public void setTestCycleId(String testCycleId) {
        this.testCycleId = testCycleId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }
}

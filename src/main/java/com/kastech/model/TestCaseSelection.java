package com.kastech.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class TestCaseSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("BusinessModule")
    @Column(name="BusinessModule")
    private String businessModule;

    private String testSCript;

    private String description;

    private String execute;

    private String numberOfIterations;

    private String startIndexForIterations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessModule() {
        return businessModule;
    }

    public void setBusinessModule(String businessModule) {
        this.businessModule = businessModule;
    }

    public String getTestSCript() {
        return testSCript;
    }

    public void setTestSCript(String testSCript) {
        this.testSCript = testSCript;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExecute() {
        return execute;
    }

    public void setExecute(String execute) {
        this.execute = execute;
    }

    public String getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(String numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public String getStartIndexForIterations() {
        return startIndexForIterations;
    }

    public void setStartIndexForIterations(String startIndexForIterations) {
        this.startIndexForIterations = startIndexForIterations;
    }
}

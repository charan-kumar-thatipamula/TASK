package com.kastech.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Table(name = "test_case_details")
public class TestCaseDetailsTable {

    @Id
    int id;

    @Column(name = "test_case_id")
    String testCaseId;

    String keyword;

    @Column(name = "object_id")
    String objectId;

    @Column(name = "keyin_data")
    String keyInData;

    @Column(name = "screenshot")
    String screenShot;

    @Column(name = "pass_log_msg")
    String passLogMsg;

    @Column(name = "fail_log_msg")
    String failLogMsg;

    @CreatedDate
    @Column(name = "created_at")
    String createAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    String modifiedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getKeyInData() {
        return keyInData;
    }

    public void setKeyInData(String keyInData) {
        this.keyInData = keyInData;
    }

    public String getPassLogMsg() {
        return passLogMsg;
    }

    public void setPassLogMsg(String passLogMsg) {
        this.passLogMsg = passLogMsg;
    }

    public String getFailLogMsg() {
        return failLogMsg;
    }

    public void setFailLogMsg(String failLogMsg) {
        this.failLogMsg = failLogMsg;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(String screenShot) {
        this.screenShot = screenShot;
    }
}

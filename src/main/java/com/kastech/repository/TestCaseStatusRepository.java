package com.kastech.repository;

import com.kastech.model.TestCaseStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseStatusRepository extends CrudRepository<TestCaseStatus, String> {
//    List<TestCaseStatus> findByRunIdActivityIdTestCycleIdTestCaseIdTestType(String runId,
//                                                                 String activityId,
//                                                                 String testCycleId,
//                                                                 String testCaseId,
//                                                                 String testType);
    @Query(
            value = "SELECT * FROM test_case_status WHERE run_id = ?1 and activity_id = ?2 and " +
                    "test_cycle_id = ?3 and test_case_id = ?4 and test_type = ?5",
            nativeQuery = true)
    List<TestCaseStatus> getEntry(String runId,
                                  String activityId,
                                  String testCycleId,
                                  String testCaseId,
                                  String testType);
}

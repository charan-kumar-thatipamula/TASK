package com.kastech.repository;

import com.kastech.model.RunTable;
import com.kastech.model.TestCaseStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunRepository extends CrudRepository<RunTable, String> {
    List<RunTable> findByRunId(String runId);
    List<RunTable> findByReportId(String reportId);

    @Query(
            value = "SELECT * FROM test_cycle_run WHERE report_id = ?1 and activity_id = ?2 and " +
                    "test_cycle_id = ?3",
            nativeQuery = true)
    List<RunTable> getEntryWithReportIdActivityIdTestCycleId(String reportId,
                                  String activityId,
                                  String testCycleId);

    @Query(
            value = "SELECT * FROM test_cycle_run WHERE report_id = ?1 and activity_id = ?2",
            nativeQuery = true)
    List<RunTable> getEntryWithReportIdActivityId(String reportId,
                                                             String activityId);
}

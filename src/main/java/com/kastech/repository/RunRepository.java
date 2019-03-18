package com.kastech.repository;

import com.kastech.model.RunTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunRepository extends CrudRepository<RunTable, String> {
    List<RunTable> findByRunId(String runId);
    List<RunTable> findByReportId(String reportId);
}

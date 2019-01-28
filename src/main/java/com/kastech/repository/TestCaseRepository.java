package com.kastech.repository;

import com.kastech.model.TestCaseDetailsTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseRepository extends CrudRepository<TestCaseDetailsTable, String> {
    List<TestCaseDetailsTable> findByTestCaseIdOrderByCreateAtAsc(String testCaseId);
}


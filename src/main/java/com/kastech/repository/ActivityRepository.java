package com.kastech.repository;

import com.kastech.model.ActivityTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends CrudRepository<ActivityTable, String> {
    List<ActivityTable> findByActivityId(String activityId);
}

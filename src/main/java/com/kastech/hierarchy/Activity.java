package com.kastech.hierarchy;

import com.kastech.model.ActivityTable;
import com.kastech.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Activity extends Entity {

    public Activity(String id) {
        this.id = id;
        this.subEntities = new ArrayList<>();
        this.runSubEntitiesParellel = true;
        logger = Logger.getLogger(Activity.class.getName());
    }

    @Override
    void runComplete() {

    }

    @Override
    Entity createSubEntity(String id) {
        return new TestCycle(id);
    }

    @Override
    List<String> getSubEntityIds() {
        // TODO: extract TestCycle ids corresponding to this ActivityTable
        ActivityRepository activityRepository = this.getBeanFactory().getActivityRepository();
        List<ActivityTable> activityTableList = activityRepository.findByActivityId(getId());
        List<String> testCycleIds = new ArrayList<>();
        for (ActivityTable activityTable: activityTableList) {
            testCycleIds.add(activityTable.getTestCycleId());
        }
        return testCycleIds;
    }

}

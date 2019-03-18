package com.kastech.hierarchy;

import com.kastech.config.ApplicationContextProvider;
import com.kastech.model.ActivityTable;
import com.kastech.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@Scope("prototype")
public class Activity extends Entity {

    @Autowired
    ApplicationContextProvider applicationContextProvider;
//    public Activity(String id) {
//        this.id = id;
//        this.subEntities = new ArrayList<>();
//        this.runSubEntitiesParellel = true;
//        logger = Logger.getLogger(Activity.class.getName());
//    }

    public void initialize(String id) {
        this.id = id;
        this.subEntities = new ArrayList<>();
        this.runSubEntitiesParellel = true;
        logger = Logger.getLogger(Activity.class.getName());
    }

    @Override
    void hookAfterRun() {

    }

    @Override
    Entity createSubEntity(String id) {
//        return new TestCycle(id);
        TestCycle testCycle = applicationContextProvider.getContext().getBean(TestCycle.class);
        testCycle.initialize(id);
        return testCycle;
    }

    @Override
    List<String> getSubEntityIds() {
        // TODO: extract TestCycle ids corresponding to this ActivityTable
        ActivityRepository activityRepository = this.getBeanFactory().getActivityRepository();
//        List<ActivityTable> activityTableList = activityRepository.findByActivityId(getId());
        List<ActivityTable> activityTableList = activityRepository.findByActivityIdAndTestType(getId(), "automation");
        List<String> testCycleIds = new ArrayList<>();
        for (ActivityTable activityTable: activityTableList) {
            testCycleIds.add(activityTable.getTestCycleId());
        }
        return testCycleIds;
    }

}

package com.kastech.hierarchy;

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
        // TODO: extract TestCycle ids corresponding to this Activity
        ActivityRepository activityRepository = this.getBeanFactory().getActivityRepository();
//        return activityRepository.findById(Integer.parseInt(this.getId()));
        return null;
    }
}

package com.kastech.hierarchy;

import com.kastech.model.ActivityTable;
import com.kastech.model.RunTable;
import com.kastech.repository.RunRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Run extends Entity  {
    public Run(String id) {
        this.id = id;
        this.subEntities = new ArrayList<>();
        this.runSubEntitiesParellel = true;
        logger = Logger.getLogger(Run.class.getName());
    }
    @Override
    Entity createSubEntity(String id) {
        return new Activity(id);
    }

    @Override
    List<String> getSubEntityIds() {
        RunRepository runRepository = this.getBeanFactory().getRunRepository();
//        return activityRepository.findById(Integer.parseInt(this.getId()));
        List<RunTable> runTableList = runRepository.findByRunId(getId());
        List<String> activityIds = new ArrayList<>();
        for (RunTable runTable : runTableList) {
            activityIds.add(runTable.getActivityId());
        }
        return activityIds;
    }
}

package com.kastech.hierarchy;

import com.kastech.config.ApplicationContextProvider;
import com.kastech.model.ActivityTable;
import com.kastech.model.RunTable;
import com.kastech.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@Scope("prototype")
public class Run extends Entity  {

    @Autowired
    ApplicationContextProvider applicationContextProvider;

//    public Run(String id) {
//        this.id = id;
//        this.subEntities = new ArrayList<>();
//        this.runSubEntitiesParellel = true;
//        logger = Logger.getLogger(Run.class.getName());
//    }

    public void initialize(String id) {
        this.id = id;
        this.subEntities = new ArrayList<>();
        this.runSubEntitiesParellel = true;
        logger = Logger.getLogger(Run.class.getName());
    }

    @Override
    Entity createSubEntity(String id) {
        Activity activity = applicationContextProvider.getContext().getBean(Activity.class);
        activity.initialize(id);
        return activity;
    }

    @Override
    List<String> getSubEntityIds() {
        RunRepository runRepository = this.getBeanFactory().getRunRepository();
//        return activityRepository.findById(Integer.parseInt(this.getId()));
        List<RunTable> runTableList = runRepository.findByReportId(getId());
        List<String> activityIds = new ArrayList<>();
        for (RunTable runTable : runTableList) {
            activityIds.add(runTable.getActivityId());
        }
        return activityIds;
    }
}

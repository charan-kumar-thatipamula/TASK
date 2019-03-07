package com.kastech.hierarchy;

import com.kastech.config.ApplicationContextProvider;
import com.kastech.hierarchy.enums.EntityType;
import com.kastech.request.ExecuteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EntityFactory {
    @Autowired
    ApplicationContextProvider applicationContextProvider;
    public Entity getEntity(ExecuteRequest executeRequest) {
//        switch (executeRequest.getEntityType()) {
//            case Run: return new Run(executeRequest.getId());
//            case Activity: return new Activity(executeRequest.getId());
//            case TestCycle: return new TestCycle(executeRequest.getId());
//            case TestCase: return new TestCase(executeRequest.getId());
//            default: return null;
//        }
        ApplicationContext context = applicationContextProvider.getContext();
        String id = executeRequest.getId();
        switch (executeRequest.getEntityType()) {
            case Run:
                Run run = context.getBean(Run.class);
                run.initialize(id);
                return run;
            case Activity:
                Activity activity = context.getBean(Activity.class);
                activity.initialize(id);
                return activity;
            case TestCycle:
                TestCycle testCycle = context.getBean(TestCycle.class);
                testCycle.initialize(id);
                return testCycle;
            case TestCase:
                TestCase testCase = context.getBean(TestCase.class);
                testCase.initialize(id);
                return testCase;
            default: return null;
        }
    }
}

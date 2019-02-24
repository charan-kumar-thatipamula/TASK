package com.kastech.hierarchy;

import com.kastech.hierarchy.enums.EntityType;
import com.kastech.request.ExecuteRequest;

public class EntityFactory {
    public static Entity getEntity(ExecuteRequest executeRequest) {
        switch (executeRequest.getEntityType()) {
            case Run: return new Run(executeRequest.getId());
            case Activity: return new Activity(executeRequest.getId());
            case TestCycle: return new TestCycle(executeRequest.getId());
            case TestCase: return new TestCase(executeRequest.getId());
            default: return null;
        }
    }
}

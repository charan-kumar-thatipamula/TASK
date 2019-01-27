package com.kastech.service;

import com.kastech.hierarchy.Activity;
import com.kastech.hierarchy.Entity;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    public void runActivity(String activityId) {
        // TODO: prefetch data and populated required data structure.
        // If not prefetch, dynamically fetch sub entities for entity in its corresponding class
        // in getSubEntityIds() method
        Entity entity = new Activity(activityId);
        entity.createSubEntities();
        entity.runEntity();
    }
}

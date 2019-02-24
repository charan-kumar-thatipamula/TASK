package com.kastech.service;

import com.kastech.hierarchy.Activity;
import com.kastech.hierarchy.Entity;
import com.kastech.hierarchy.EntityFactory;
import com.kastech.request.ExecuteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    @Autowired
    BeanFactory beanFactory;
    public void runEntity(ExecuteRequest executeRequest) {
        // TODO: prefetch data and populated required data structure.
        // If not prefetch, dynamically fetch sub entities for entity in its corresponding class
        // in getSubEntityIds() method
        Entity entity = EntityFactory.getEntity(executeRequest);
        entity.setBeanFactory(beanFactory);
        entity.createSubEntities();
        entity.runEntity();
    }
}

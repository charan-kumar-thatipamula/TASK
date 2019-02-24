package com.kastech.request;

import com.kastech.hierarchy.enums.EntityType;

public class ExecuteRequest {
    String id;

    EntityType entityType = EntityType.Run;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }
}

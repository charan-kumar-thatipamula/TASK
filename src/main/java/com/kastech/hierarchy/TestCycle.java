package com.kastech.hierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class TestCycle extends Entity {

    TestCycle(String id) {
        this.id = id;
        this.subEntities = new ArrayList<>();
        this.runSubEntitiesParellel = true;
        logger = Logger.getLogger(TestCycle.class.getName());
    }

    @Override
    void runComplete() {

    }

    @Override
    Entity createSubEntity(String id) {
        return new TestCase(id);
    }

    @Override
    List<String> getSubEntityIds() {
        // TODO: Extract TestCase ids corresponding to this TestCycle
        return null;
    }
}

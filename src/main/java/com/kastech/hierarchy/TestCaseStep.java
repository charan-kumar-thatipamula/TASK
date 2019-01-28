package com.kastech.hierarchy;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestCaseStep extends Entity {

    TestCaseStep(String id) {
        this.id = id;
        this.subEntities = null;
        this.runSubEntitiesParellel = false;
        logger = Logger.getLogger(TestCaseStep.class.getName());
    }

    @Override
    public void runEntity() {
        // TODO: Invoke logic corresponding to the TestCaseStep
        logger.log(Level.INFO, "******Running test case: " + id + "******");
    }

    @Override
    void runComplete() {

    }

    @Override
    Entity createSubEntity(String id) {
        // return null as TestCaseStep will not have any sub entities
        return null;
    }

    @Override
    List<String> getSubEntityIds() {
        // TestCaseStep will not have any sub entities. Nothing to do here.
        return null;
    }
}

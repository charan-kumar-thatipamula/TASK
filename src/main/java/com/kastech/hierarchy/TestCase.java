package com.kastech.hierarchy;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class TestCase extends Entity {

    TestCase(String id) {
        this.id = id;
        this.subEntities = new LinkedList<>();
        this.runSubEntitiesParellel = false;
        logger = Logger.getLogger(TestCase.class.getName());
    }

    @Override
    void runComplete() {

    }

    @Override
    Entity createSubEntity(String id) {
        return new TestCaseStep(id);
    }

    @Override
    List<String> getSubEntityIds() {
        // TODO: Extract TestCaseStep ids corresponding to this TestCase
        return null;
    }
}

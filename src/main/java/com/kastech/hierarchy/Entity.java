package com.kastech.hierarchy;


import com.kastech.service.BeanFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Entity {

    String id;
    BeanFactory beanFactory;
    List<Entity> subEntities;
    boolean runSubEntitiesParellel;
    Logger logger;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public List<Entity> getSubEntities() {
        return subEntities;
    }

    public void setSubEntities(List<Entity> subEntities) {
        this.subEntities = subEntities;
    }

    public boolean isRunSubEntitiesParellel() {
        return runSubEntitiesParellel;
    }

    public void setRunSubEntitiesParellel(boolean runSubEntitiesParellel) {
        this.runSubEntitiesParellel = runSubEntitiesParellel;
    }

    void addSubEntity(Entity entity) {
        if (this.subEntities == null) {
            subEntities = new LinkedList<>();
        }
        subEntities.add(entity);
    }

    public void createSubEntities() {
        List<String> subEntityIds = getSubEntityIds();
        for (String id : subEntityIds) {
            Entity subEntity = createSubEntity(id);
            if (subEntity != null) {
                subEntity.setBeanFactory(this.getBeanFactory());
                addSubEntity(subEntity);
                subEntity.createSubEntities();
            }
        }
    }

    public void runEntity() {
        if (isRunSubEntitiesParellel()) {
            runSubEntitiesParellel();
        } else {
            runSubEntitiesInSequence();
        }
        runComplete();
    }

    void runSubEntitiesParellel() {
        CountDownLatch countDownLatch = new CountDownLatch(this.getSubEntities().size());
        for (Entity entity : subEntities) {
            try {
                entity.runEntity();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error while running enitty: " + entity.getClass().getName());
            } finally {
                countDownLatch.countDown();
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void runSubEntitiesInSequence () {
        for (Entity entity : subEntities) {
            try {
                entity.runEntity();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error while running enitty: " + entity.getClass().getName());
            }
        }
    }
    void runComplete() {
        logger.log(Level.INFO, "Completed all running all sub entries for: " + this.getClass().getName());
    }
    abstract Entity createSubEntity(String id);
    abstract List<String> getSubEntityIds();
}

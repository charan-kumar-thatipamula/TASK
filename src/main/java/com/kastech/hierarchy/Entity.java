package com.kastech.hierarchy;


import com.kastech.service.BeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope("prototype")
public abstract class Entity {

    String id;
    BeanFactory beanFactory;
    List<Entity> subEntities;
    boolean runSubEntitiesParellel;
    Logger logger;
    Entity superEntity;

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

    public Entity getSuperEntity() {
        return superEntity;
    }

    public void setSuperEntity(Entity superEntity) {
        this.superEntity = superEntity;
    }

    void addSubEntity(Entity entity) {
        if (this.subEntities == null) {
            subEntities = new LinkedList<>();
        }
        subEntities.add(entity);
    }

    public void createSubEntities() {
        List<String> subEntityIds = getSubEntityIds();
        if (subEntityIds == null) {
            return;
        }
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
        logger.log(Level.INFO, "Running entity: " + id);
//        if (isRunSubEntitiesParellel()) {
//            runSubEntitiesParellel();
//        } else {
//            runSubEntitiesInSequence();
//        }
        hookBeforeRun();
        runSubEntitiesInSequence();
        hookAfterRun();
        logger.log(Level.INFO, "Completed running entity: " + id);
    }

    public void hookBeforeRun() {
    }

    void runSubEntitiesParellel() {
        CountDownLatch countDownLatch = new CountDownLatch(this.getSubEntities().size());
        for (Entity entity : subEntities) {
            TaskExecutor taskExecutor = this.getBeanFactory().getTaskExecutor();
            try {
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        entity.runEntity();
                    }
                });

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
    void hookAfterRun() {
        logger.log(Level.INFO, "Completed all running all sub entries for: " + this.getClass().getName());
    }
    abstract Entity createSubEntity(String id);
    abstract List<String> getSubEntityIds();
}

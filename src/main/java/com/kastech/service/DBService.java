package com.kastech.service;

import com.kastech.hierarchy.*;
import com.kastech.model.RunTable;
import com.kastech.model.TenantSetup;
import com.kastech.repository.RunRepository;
import com.kastech.repository.TenantSetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DBService {
    Logger logger = Logger.getLogger(DBService.class.getName());
    @Autowired
    RunRepository runRepository;
    @Autowired
    TenantSetupRepository tenantSetupRepository;

    @Value("${include.testcycle.for.url}")
    boolean useTestCycleId;

    public String getUrlForEntity(Entity entity) {
        if (entity == null) {
            return null;
        }

        if (entity instanceof TestCase) {
            TestCase testCase = (TestCase) entity;
            TestCycle testCycle = (TestCycle) testCase.getSuperEntity();
            Activity activity = (Activity) testCycle.getSuperEntity();
            Run run = (Run) activity.getSuperEntity();
            List<RunTable> runTableList = null;
            if (useTestCycleId) {
                runTableList = runRepository.getEntryWithReportIdActivityIdTestCycleId(run.getId(), activity.getId(), testCycle.getId());
            } else {
                runTableList = runRepository.getEntryWithReportIdActivityId(run.getId(), activity.getId());
            }
            if (runTableList == null || runTableList.isEmpty()) {
                logger.log(Level.SEVERE, "No entry found in test_cycle_run table");
                return null;
            }
            String tenant = runTableList.get(0).getTenant();
            List<TenantSetup> tenantSetupList = tenantSetupRepository.findByTenant(tenant);
            if (tenantSetupList == null || tenantSetupList.isEmpty()) {
                logger.log(Level.SEVERE, "No entry found in tenant_setup table");
                return null;
            }
            String url = tenantSetupList.get(0).getUrl();
            logger.log(Level.INFO, "fetched url for tenant: " + tenant + "; url: " + url);
            return url;
        }
        return null;
    }
}

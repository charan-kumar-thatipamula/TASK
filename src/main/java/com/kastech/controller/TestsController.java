package com.kastech.controller;

import com.kastech.DriverScript.StandaloneDriver;
import com.kastech.request.ExecuteRequest;
import com.kastech.service.EntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
public class TestsController {
    @Autowired
    StandaloneDriver standaloneDriver;
    @Autowired
    EntityService entityService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value="/runtests", method= RequestMethod.POST)
    public String execute(@RequestBody Map<String, Object> input) {
        try {
            String username = (String) input.get("userName");
            String passWord = (String) input.get("passWord");
            String runId = (String) input.get("runId");
            String tenant = (String) input.get("tenant");
            String browsertype = (String) input.get("browser");
            Boolean rescind = (Boolean) input.get("rescind");
            standaloneDriver.execute(username,passWord,runId,tenant,browsertype,rescind);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Failed";
        }
        return "Success";
    }

    @RequestMapping(value="/runtestcases", method= RequestMethod.POST)
    public ResponseEntity<String> executeTestCases(@RequestBody ExecuteRequest executeRequest) {
        try {
//            String username = (String) input.get("userName");
//            String passWord = (String) input.get("passWord");
//            String runId = (String) input.get("runId");
//            String tenant = (String) input.get("tenant");
//            String browsertype = (String) input.get("browser");
//            Boolean rescind = (Boolean) input.get("rescind");
//            standaloneDriver.execute(username,passWord,runId,tenant,browsertype,rescind);
            entityService.runEntity(executeRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            return "Failed";
        }
//        return "Success";
//        return ResponseEntity<String>
        return null;
    }
}

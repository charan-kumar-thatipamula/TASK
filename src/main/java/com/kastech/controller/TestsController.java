package com.kastech.controller;

import com.kastech.DriverScript.StandaloneDriver;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@RestController
public class TestsController {
    @Autowired
    StandaloneDriver standaloneDriver;

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
}

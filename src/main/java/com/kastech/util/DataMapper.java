package com.kastech.util;

import com.kastech.model.TestCaseDetailsTable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class DataMapper {

    public Map<String, String> getFieldMappingForTestCaseSteps() {
        Map<String, String> map = new HashMap<>();
        map.put("testCaseId","TestScript");
        map.put("keyword", "Keyword");
        map.put("objectId","ObjectID");
        map.put("passLogMsg","OnPassLogMsg");
        map.put("failLogMsg","OnFailLogMsg");
        map.put("screenShot","PassScreenshot");
        map.put("keyInData","KeyInData");
        return map;
    }

    public static Map<String, List<String>> getMapFromTableEntry(List<TestCaseDetailsTable> testCaseSteps) {
        Map<String, List<String>> map = new HashMap<>();
        for (TestCaseDetailsTable testCaseDetailsTable : testCaseSteps) {
            map.computeIfAbsent("TestScript", k -> new LinkedList<>());
            map.get("TestScript").add(testCaseDetailsTable.getTestCaseId() == null ? "" : testCaseDetailsTable.getTestCaseId());

            map.computeIfAbsent("Keyword", k -> new LinkedList<>());
            map.get("Keyword").add(testCaseDetailsTable.getKeyword() == null ? "" : testCaseDetailsTable.getKeyword());

            map.computeIfAbsent("ObjectID", k -> new LinkedList<>());
            map.get("ObjectID").add(testCaseDetailsTable.getObjectId() == null ? "" : testCaseDetailsTable.getObjectId());

            map.computeIfAbsent("OnPassLogMsg", k -> new LinkedList<>());
            map.get("OnPassLogMsg").add(testCaseDetailsTable.getPassLogMsg() == null ? "" : testCaseDetailsTable.getPassLogMsg());

            map.computeIfAbsent("OnFailLogMsg", k -> new LinkedList<>());
            map.get("OnFailLogMsg").add(testCaseDetailsTable.getFailLogMsg() == null ? "" : testCaseDetailsTable.getFailLogMsg());

            map.computeIfAbsent("PassScreenshot", k -> new LinkedList<>());
            map.get("PassScreenshot").add(testCaseDetailsTable.getScreenShot() == null ? "" : testCaseDetailsTable.getScreenShot());

            map.computeIfAbsent("KeyInData", k -> new LinkedList<>());
            map.get("KeyInData").add(testCaseDetailsTable.getKeyInData() == null ? "" : testCaseDetailsTable.getKeyInData());
        }
        return map;
    }
}

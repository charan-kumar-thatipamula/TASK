package com.kastech.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class QueryResult {
    Map<Integer, List<List<String>>> runIdQueryResultMap;
}

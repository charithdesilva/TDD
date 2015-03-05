package com.aconex.codechallenge.service;

import java.util.List;
import java.util.Map;

public interface ReportingService {

    /**
     * @param phoneNumberWordMap
     */
    void displayReport(Map<String, List<String>> phoneNumberWordMap);

}
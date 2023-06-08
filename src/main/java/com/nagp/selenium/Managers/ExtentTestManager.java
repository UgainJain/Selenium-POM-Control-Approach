package com.nagp.selenium.Managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

// Manager fo managing the Extent test in reports
public class ExtentTestManager {
    //Bidi map to store the test with the thread id
    static BidiMap<Integer, ExtentTest> extentTestMap = new DualHashBidiMap<Integer,ExtentTest>();
    static ExtentReports extent = ExtentReportManager.getInstance();

    // Current Running Test Getter
    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTestReporting() {
        extent.flush();
    }

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
    public static synchronized void endTest(){
        ExtentTest test = getTest();
        extentTestMap.removeValue(test);
    }
}

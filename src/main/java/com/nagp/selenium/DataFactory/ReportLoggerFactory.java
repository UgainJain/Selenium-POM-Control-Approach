package com.nagp.selenium.DataFactory;

import com.aventstack.extentreports.Status;
import com.nagp.selenium.Managers.ExtentTestManager;

//Report logger to add logs to reports
public final class ReportLoggerFactory {
    public static void AddInfo(String logInfo)
    {
        ExtentTestManager.getTest().log(Status.INFO,logInfo);
    }
    public static void AddFail(String logInfo)
    {
        ExtentTestManager.getTest().log(Status.FAIL,logInfo);
    }
    public static void AddSkip(String logInfo)
    {
        ExtentTestManager.getTest().log(Status.SKIP,logInfo);
    }
    public static void AddWarning(String logInfo)
    {
        ExtentTestManager.getTest().log(Status.WARNING,logInfo);
    }
    public static void AddPass(String logInfo)
    {
        ExtentTestManager.getTest().log(Status.PASS,logInfo);
    }
    public static void AddScreenshot(String screenshotPath)
    {
        ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
    }
}

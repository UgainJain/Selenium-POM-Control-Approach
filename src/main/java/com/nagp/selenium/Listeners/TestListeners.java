package com.nagp.selenium.Listeners;

import com.aventstack.extentreports.Status;
import com.nagp.selenium.DataFactory.ReportLoggerFactory;
import com.nagp.selenium.Managers.ExtentTestManager;
import com.nagp.selenium.Managers.ScreenShotManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Date;

// TestNg TestListeners for reporting te test failures and passing
public class TestListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        if(iTestResult.getThrowable() != null){
            ReportLoggerFactory.AddInfo("Exception encountered :"+
                    iTestResult.getThrowable().getClass().getName());
            if(iTestResult.getThrowable().getMessage() != null)
                ReportLoggerFactory.AddInfo(iTestResult.getThrowable().getMessage());
        }
        String timeStamp;
        Date d = new Date();
        timeStamp = d.toString().replace(":", "_").replace(" ", "_");
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        String methodName = iTestResult.getMethod().getMethodName();
        String imageName = methodName+""+timeStamp+".png";
        try {
            ReportLoggerFactory.AddScreenshot(ScreenShotManager.CaptureScreenShot(imageName,driver)); // taking scrsht with name and adding to report
        } catch (IOException e) {
            ReportLoggerFactory.AddInfo(e.getCause().toString());
        }
        ExtentTestManager.getTest().log(Status.FAIL, "Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ReportLoggerFactory.AddSkip( "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but within percentage " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext iTestContext) {    }

    @Override
    public void onFinish(ITestContext iTestContext) {    }


}

package com.nagp.selenium.Listeners;

import com.nagp.selenium.Managers.ExtentTestManager;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;


//Invoke method Listener for starting the test with custom test name or method name
public class InvokeMethodListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        if(iInvokedMethod.isTestMethod()) {
            if (!GetCustomTestName(iTestResult).equals(""))
                ExtentTestManager.startTest(GetCustomTestName(Reporter.getCurrentTestResult()));
            else
                ExtentTestManager.startTest(iInvokedMethod.getTestMethod().getMethodName());
        }
    }
    private String GetCustomTestName(ITestResult result)
    {
        if(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class)!= null)
            return result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
        return "";
    }


    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
    }
}

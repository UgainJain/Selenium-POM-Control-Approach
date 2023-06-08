package com.nagp.selenium.SeleniumPOMAmazon;

import com.nagp.selenium.ApplicationFactory;
import com.nagp.selenium.Driver.Driver;
import com.nagp.selenium.Listeners.InvokeMethodListener;
import com.nagp.selenium.Listeners.SuiteListeners;
import com.nagp.selenium.Listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
//Base Test for all the test classes
@Listeners({TestListeners.class, SuiteListeners.class ,InvokeMethodListener.class})
public class BaseTest {
    public WebDriver webDriver = null;
    private Driver driverObj;

    @BeforeMethod
    public void beforeTest(ITestContext context) {
        this.driverObj = new Driver();
        this.webDriver =  driverObj.invokeDriver();
        this.driverObj.openURL(this.webDriver);
        ApplicationFactory.setDriver(this.webDriver);
        context.setAttribute("driver",this.webDriver);

    }

    @AfterMethod
    public void AfterEachTest(ITestContext testResult)
    {
        webDriver.quit();
    }


}

package com.nagp.Shared.Extentions;

import com.nagp.selenium.Constants.ConfigConstants;
import com.nagp.selenium.DataFactory.DataConverters;
import com.nagp.selenium.Utils.LoadProperty;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

// To be used for extra webdriver or element related methods
public final class WebDriverExtension {

    public static void OpenUrlInNewTab(WebDriver driver, String url)
    {
        Set<String> currentHandles= driver.getWindowHandles();
        ExecuteJavaScript(driver,"window.open()");
        Set<String> handles=driver.getWindowHandles();
        for(String actual: handles)
        {
            if(!currentHandles.contains(actual))
            {
                //switching to the opened tab
                driver.switchTo().window(actual);
                //opening the URL saved.
                driver.get(url);
            }
        }
    }
    public static void ExecuteJavaScript(WebDriver driver, String script)
    {
        ((JavascriptExecutor)driver).executeScript(script);
    }
    //Clicking element using javascript
    public static void ClickUsingJavaScript( WebElement element)
    {
        WebDriver driver = ((WrapsDriver)element).getWrappedDriver();
        JavascriptExecutor executor = ((JavascriptExecutor)driver);
        executor.executeScript("arguments[0].click();", element);
    }
    //Explicit waiter for element clickable
    public static WebElement WaitForElementToBeClickable(WebElement element)
    {
        WebDriver driver = ((WrapsDriver)element).getWrappedDriver();
        return new WebDriverWait(driver, DataConverters.GetLongValue(LoadProperty.getValueForConstant(ConfigConstants.waitTimeout)))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
    //Explicit waiter for element visibility
    public static WebElement WaitForElementToBeVisible(WebElement element)
    {
        WebDriver driver = ((WrapsDriver)element).getWrappedDriver();
        return new WebDriverWait(driver, DataConverters.GetLongValue(LoadProperty.getValueForConstant(ConfigConstants.waitTimeout)))
                .until(ExpectedConditions.visibilityOf(element));
    }
}

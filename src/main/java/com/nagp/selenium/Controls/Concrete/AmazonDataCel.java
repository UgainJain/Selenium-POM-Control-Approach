package com.nagp.selenium.Controls.Concrete;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Controls.Controls.IAmazonDataCel;
import com.nagp.selenium.DataFactory.DataConverters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
/*
    This control is specifically created for amazon and can not be reused is any other application.
    Thus, name includes the application name.
    The control name also include the widget used for creating this control.
    Depicts each card product card available in the results
 */
public class AmazonDataCel extends WebElementWrapped implements IAmazonDataCel {
    public AmazonDataCel(WebElement element) {
        super(element);
    }

    @Override
    public String GetTitle() {
        return WebDriverExtension.
                WaitForElementToBeVisible(element.findElement(By.cssSelector("span.celwidget h2"))).getText();
    }

    @Override
    public String GetDataIndex() {
        return element.getAttribute("data-index");
    }

    @Override
    public float GetCost() {
        try {
            return DataConverters.GetFloatValue(WebDriverExtension.
                    WaitForElementToBeVisible(element.findElement(By.cssSelector("span.a-price-whole"))).getText());
        }
        catch (NoSuchElementException e)
        {
            return 0;
        }
    }

    @Override
    public float GetRating() {
        try {
        return DataConverters.GetFirstFloatFromString(new RatingLabel(element).getAverageRating());
        }
        catch (NoSuchElementException e)
        {
            return 0;
        }
    }

    @Override
    public String GetUrlLink() {
        return element.findElement(By.cssSelector("h2 a[href]")).getAttribute("href");
    }
}

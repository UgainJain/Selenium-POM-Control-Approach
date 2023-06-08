package com.nagp.selenium.Controls.Concrete;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Controls.Controls.ILink;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;

//Link control implementation
public class Link extends WebElementWrapped implements ILink {

    public Link(WebElement element) {
        super(element);
    }

    @Override
    public String GetText() {
        return WebDriverExtension.WaitForElementToBeVisible(element.findElement(By.tagName("span"))).getText();
    }
    @Override
    public String GetLabel() {
        return element.findElement(By.cssSelector("[aria-label]")).getAttribute("aria-label");
    }

    @Override
    public void Click() {
        try {
            WebDriverExtension.WaitForElementToBeClickable(element.findElement(By.cssSelector("span a"))).click();
        }
        catch (ElementNotInteractableException e)
        {
            WebDriverExtension.WaitForElementToBeClickable(element).click();
        }
    }

    @Override
    public String GetLink() {
        if(element.getAttribute("href") == null || element.getAttribute("href").equals(""))
        {
            return element.findElement(By.tagName("a")).getAttribute("href");
        }
        else
            return element.getAttribute("href");
    }
}

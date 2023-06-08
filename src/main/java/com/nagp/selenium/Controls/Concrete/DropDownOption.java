package com.nagp.selenium.Controls.Concrete;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Controls.Controls.IDropDownOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

//DropDownOption control implementation
public class DropDownOption extends WebElementWrapped implements IDropDownOption {
    public DropDownOption(WebElement element) {
        super(element);
    }

    @Override
    public String GetOptionValue() {
        return element.getAttribute("textContent").trim();
    }

    @Override
    public void Select() {
        try {
            WebDriverExtension.ClickUsingJavaScript(element.findElement(By.cssSelector("a")));
        }
        catch (NoSuchElementException e)
        {
            WebDriverExtension.ClickUsingJavaScript(element);
        }
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }
}

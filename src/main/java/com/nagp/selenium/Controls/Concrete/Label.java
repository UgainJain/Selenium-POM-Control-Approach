package com.nagp.selenium.Controls.Concrete;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Controls.Controls.ILabel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//Label control implementation
public class Label extends WebElementWrapped implements ILabel {
    public Label(WebElement element) {
        super(element);
    }

    @Override
    public String GetText() {
        return WebDriverExtension.WaitForElementToBeVisible(element).getText().trim();
    }
}

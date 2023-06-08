package com.nagp.selenium.Controls.Concrete;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Controls.Controls.IButton;
import org.openqa.selenium.WebElement;
//Button control implementation
public class Button extends WebElementWrapped implements IButton {
    public Button(WebElement element) {
        super(element);
    }

    @Override
    public void Click() {
        WebDriverExtension.WaitForElementToBeClickable(element).click();

    }

    @Override
    public String GetText() {
        return WebDriverExtension.WaitForElementToBeVisible(element).getText();
    }
}

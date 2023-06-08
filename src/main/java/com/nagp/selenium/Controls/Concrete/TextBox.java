package com.nagp.selenium.Controls.Concrete;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Controls.Controls.ITextBox;
import org.openqa.selenium.WebElement;

//Textbox control implementation
public class TextBox extends WebElementWrapped implements ITextBox {

    public TextBox(WebElement element) {
        super(element);
    }

    @Override
    public void SetText(String text) {
        element.clear();
        element.sendKeys(text);
    }

    @Override
    public String GetText() {
        return WebDriverExtension.WaitForElementToBeVisible(element).getText();
    }

    @Override
    public void clearText() {
        WebDriverExtension.WaitForElementToBeVisible(element).clear();
    }
}

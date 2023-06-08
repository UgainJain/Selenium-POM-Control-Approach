package com.nagp.selenium.Controls.Concrete;


import org.openqa.selenium.WebElement;

import com.nagp.selenium.Controls.Controls.IWebElement;

//Base control implementation for every control
public class WebElementWrapped implements IWebElement{
	protected WebElement element;

	public WebElementWrapped(final WebElement element) {
        this.element = element;
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }

    @Override
    public boolean IsDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public boolean IsEnabled() {
        return element.isEnabled();
    }
}

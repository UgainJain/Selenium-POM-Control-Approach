package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.Button;
// Control for type Button
@ImplementedIn(Button.class)
public interface IButton extends IWebElement{
    void Click();
    String GetText();
}

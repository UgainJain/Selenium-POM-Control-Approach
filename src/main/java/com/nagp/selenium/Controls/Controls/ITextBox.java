package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.TextBox;

// Control for type textbox
@ImplementedIn(TextBox.class)
public interface ITextBox extends IWebElement{
    void SetText(String text);
    String GetText();
    void clearText();
}

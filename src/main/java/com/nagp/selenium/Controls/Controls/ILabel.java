package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.Label;


//Control for type TextLabel
@ImplementedIn(Label.class)
public interface ILabel extends IWebElement{
    String GetText();
}

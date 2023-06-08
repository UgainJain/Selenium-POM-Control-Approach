package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.Link;

//Control for type Link
@ImplementedIn(Link.class)
public interface ILink extends IWebElement{
    String GetText();
    void Click();
    String GetLink();
    String GetLabel();
}

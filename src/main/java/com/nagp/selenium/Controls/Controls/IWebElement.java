package com.nagp.selenium.Controls.Controls;

import org.openqa.selenium.WrapsElement;

//BaseControl for every control
public interface IWebElement extends WrapsElement{

    boolean IsDisplayed();
    boolean IsEnabled();

}

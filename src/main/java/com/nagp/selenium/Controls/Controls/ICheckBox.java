package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.CheckBox;
// Control for type CheckBox
@ImplementedIn(CheckBox.class)
public interface ICheckBox extends IWebElement {
    void toggle();
    void check();
    void uncheck();
    boolean isChecked();
}

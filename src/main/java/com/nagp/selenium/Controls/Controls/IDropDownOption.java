package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.DropDownOption;

//Control for type DropDown option
@ImplementedIn(DropDownOption.class)
public interface IDropDownOption extends IWebElement{
    String GetOptionValue();
    void Select();
    boolean isSelected();
}

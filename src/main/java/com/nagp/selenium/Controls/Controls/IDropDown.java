package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.DropDown;

import java.util.List;

//Control for type DropDown
@ImplementedIn(DropDown.class)
public interface IDropDown extends IWebElement{
    void Select(String selection);
    String GetSelectedItem();
    List<String> GetOptionsValue();
}

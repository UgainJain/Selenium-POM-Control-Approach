package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.AmazonDataCel;
/*
    This control is specifically created for amazon and can not be reused is any other application.
    Thus, name includes the application name.
    The control name also include the widget used for creating this control.
    Depicts each card product card available in the results
 */
@ImplementedIn(AmazonDataCel.class)
public interface IAmazonDataCel extends IWebElement {
    String GetTitle();
    String GetDataIndex();
    float GetCost();
    float GetRating();
    String GetUrlLink();
}

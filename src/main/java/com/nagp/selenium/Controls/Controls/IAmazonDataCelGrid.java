package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.AmazonDataCelGrid;
import java.util.List;
/*
    This control is specifically created for amazon and can not be reused is any other application.
    Thus, name includes the application name.
    The control name also include the widget used for creating this control.
    Depicts the whole grid of cel widget for the result.
 */
@ImplementedIn(AmazonDataCelGrid.class)
public interface IAmazonDataCelGrid extends IWebElement{
    List<IAmazonDataCel> getAllCards();
    List<IAmazonDataCel> getAllCardsWithoutAds();
    IAmazonDataCel getTopResult();
    IAmazonDataCel getCardWithTitle(String title);
    int GetVisibleResultsCount();

}

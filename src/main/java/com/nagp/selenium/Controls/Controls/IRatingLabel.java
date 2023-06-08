package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.RatingLabel;

//Control for type Rating Display
@ImplementedIn(RatingLabel.class)
public interface IRatingLabel extends IWebElement{
    String getAverageRating();
}

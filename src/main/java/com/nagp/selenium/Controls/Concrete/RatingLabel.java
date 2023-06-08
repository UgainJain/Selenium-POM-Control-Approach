package com.nagp.selenium.Controls.Concrete;

import com.nagp.selenium.Controls.Controls.IRatingLabel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//RatingLabel control implementation
public class RatingLabel extends WebElementWrapped implements IRatingLabel {
    public RatingLabel(WebElement element) {
        super(element);
    }

    @Override
    public String getAverageRating() {
        return element.findElement(By.cssSelector("[class*='a-icon-alt']")).
                getAttribute("textContent");
    }
}

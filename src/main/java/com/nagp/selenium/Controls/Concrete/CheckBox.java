package com.nagp.selenium.Controls.Concrete;

import org.openqa.selenium.WebElement;
import com.nagp.selenium.Controls.Controls.ICheckBox;
/**
 * Wrapper class that wraps basic checkbox functionality.
 */
public class CheckBox extends WebElementWrapped implements ICheckBox {

	/**
     * Wraps a WebElement with checkbox functionality.
     *
     * @param element to wrap up
     */
    public CheckBox(WebElement element) {
        super(element);
    }

    public void toggle() {
        element.click();
    }

    public void check() {
        if (!isChecked()) {
            toggle();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            toggle();
        }
    }

    public boolean isChecked() {
        return element.isSelected();
    }
}

package com.nagp.selenium.Controls.Concrete;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Controls.Controls.ICartItem;
import com.nagp.selenium.Controls.Controls.ICheckBox;
import com.nagp.selenium.Controls.Controls.IPopOverDropDown;
import com.nagp.selenium.DataFactory.DataConverters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//CartItem control implementation
public class CartItem extends WebElementWrapped implements ICartItem {
    private IPopOverDropDown quantityDropdown ;
    private ICheckBox giftCheckBox ;
    public CartItem(WebElement element) {
        super(element);
        quantityDropdown = new PopOverDropDown(element.findElement(By.cssSelector("[class*='sc-action-quantity']")));
        giftCheckBox = new CheckBox(element.findElement(By.cssSelector("[class*='sc-gift-option']")));
    }

    @Override
    public String GetTitle() {
        return WebDriverExtension.WaitForElementToBeVisible(element.findElement(By.cssSelector("[class*='sc-product-title']")))
                .getText().trim();
    }

    @Override
    public float GetPrice() {
        return DataConverters.GetFloatValue(WebDriverExtension.WaitForElementToBeVisible(element.findElement(By.cssSelector("[class*='sc-product-price']")))
                .getText().trim());
    }

    @Override
    public void SelectQuantity(int quantity) {
        quantityDropdown.Select(String.valueOf(quantity));
    }

    @Override
    public int GetSelectedQuantity() {
        return Integer.parseInt(quantityDropdown.GetSelectedItem());
    }

    @Override
    public void GiftCheckbox(boolean isAGift) {
        giftCheckBox.check();
    }

    @Override
    public boolean GetGiftCheckboxValue() {
        return giftCheckBox.isChecked();
    }

    @Override
    public void Delete() {
        new Button(element.findElement(By.cssSelector("[class*='a-truncate sc-product-title']"))).Click();
    }
}

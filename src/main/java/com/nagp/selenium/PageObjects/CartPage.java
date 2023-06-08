package com.nagp.selenium.PageObjects;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Base.CustomPageFactory.Annotations.FindBy;
import com.nagp.selenium.Constants.ElementLocators;
import com.nagp.selenium.Controls.Controls.ICartItem;
import com.nagp.selenium.Controls.Controls.ILabel;
import com.nagp.selenium.DataFactory.DataConverters;
import org.openqa.selenium.WebDriver;

import java.util.List;

// Amazon Cart page
public class CartPage extends BasePage{
    public CartPage(WebDriver _webDriver) {
        super(_webDriver);
    }

    @FindBy(css = ElementLocators.CartItems)
    private List<ICartItem> CartItems;
    @FindBy(id = ElementLocators.SubTotals)
    private ILabel Subtotal;

    public String GetTopCartItemTitle()
    {
        WebDriverExtension.WaitForElementToBeVisible(Subtotal.getWrappedElement());
        return CartItems.stream().findFirst().orElse(null).GetTitle();
    }
    public float GetTopCartItemPrice()
    {
        WebDriverExtension.WaitForElementToBeVisible(Subtotal.getWrappedElement());
        return CartItems.stream().findFirst().orElse(null).GetPrice();
    }
    public float GetSubTotalValue()
    {
        return DataConverters.GetFloatValue(Subtotal.GetText());
    }

    public void SelectQuantityForCartItem(String itemTitle,int quantity)
    {
        WebDriverExtension.WaitForElementToBeVisible(Subtotal.getWrappedElement());
        CartItems.stream().filter(item->item.GetTitle().equals(itemTitle)).findFirst().orElse(null).SelectQuantity(quantity);
    }
}

package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.CartItem;
// Control for type CartItem
@ImplementedIn(CartItem.class)
public interface ICartItem extends IWebElement{
    String GetTitle();
    float GetPrice();
    void SelectQuantity(int quantity);
    int GetSelectedQuantity();
    void GiftCheckbox(boolean isAGift);
    boolean GetGiftCheckboxValue();
    void Delete();
}

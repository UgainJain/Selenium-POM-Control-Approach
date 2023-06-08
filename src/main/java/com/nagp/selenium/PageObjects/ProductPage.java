package com.nagp.selenium.PageObjects;

import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Base.CustomPageFactory.Annotations.FindBy;
import com.nagp.selenium.Constants.ElementLocators;
import com.nagp.selenium.Controls.Controls.IButton;
import com.nagp.selenium.Controls.Controls.IDropDown;
import com.nagp.selenium.Controls.Controls.ILabel;
import com.nagp.selenium.Controls.Controls.IRatingLabel;
import com.nagp.selenium.DataFactory.DataConverters;
import com.nagp.selenium.DataFactory.ReportLoggerFactory;
import org.openqa.selenium.WebDriver;
// Product details page

public class ProductPage extends BasePage{
    public ProductPage(WebDriver _webDriver) {
        super(_webDriver);
    }
    @FindBy(id = ElementLocators.ProductTitle)
    private ILabel ProductTitleLabel;
    @FindBy(id = ElementLocators.ProductPrice)
    private ILabel ProductPriceLabel;
    @FindBy(id = ElementLocators.ProductRating)
    private IRatingLabel ProductRatingLabel;
    @FindBy(id = ElementLocators.ProductQuantity)
    private IDropDown ProductQuantityDropDown;
    @FindBy(id = ElementLocators.AddToCart)
    private IButton AddToCartButton;

    public String GetProductTitle()
    {
        return ProductTitleLabel.GetText();
    }
    public float GetProductRating()
    {
        return DataConverters.GetFirstFloatFromString(ProductRatingLabel.getAverageRating());
    }
    public float GetProductPrice()
    {
        return DataConverters.GetFirstFloatFromString(ProductPriceLabel.GetText());
    }
    public void SelectProductQuantity(int quantity)
    {
        ProductQuantityDropDown.Select(String.valueOf(quantity));
        ReportLoggerFactory.AddInfo("Selecting product quantity as :" +quantity );
    }

    public  void SelectAddToCart()
    {
        AddToCartButton.Click();
        ReportLoggerFactory.AddInfo("Adding product to cart." );
    }
    public void OpenCartPage()
    {
        WebDriverExtension.OpenUrlInNewTab(webDriver,NavCartButton.GetLink());
        ReportLoggerFactory.AddInfo("Navigating to Cart" );
    }
}

package com.nagp.selenium.SeleniumPOMAmazon;

import com.nagp.Shared.Entitites.ProductInfo;
import com.nagp.selenium.ApplicationFactory;
import com.nagp.selenium.DataFactory.DataConverters;
import com.nagp.selenium.DataFactory.DataProviderFactory;
import com.nagp.selenium.DataFactory.ReportLoggerFactory;
import com.nagp.selenium.PageObjects.CartPage;
import com.nagp.selenium.PageObjects.HomePage;
import com.nagp.selenium.PageObjects.ProductPage;
import com.nagp.Shared.Pages;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest{

    @Test(testName = "Adding product to cart and changing quantity in cart reflects", dataProvider = "SearchWithQuantity", dataProviderClass = DataProviderFactory.class)
    public void AddingProductToCartWithQuantityShowsUpInTheCartWithQuantity(String searchQuery,int quantity) {
        HomePage homePage = (HomePage) ApplicationFactory.GetPage(Pages.HomePage);
        homePage.Search(searchQuery);
        ProductInfo productInfo  = homePage.GetTopResultProductInfo();
        homePage.OpenProductPage();
        ProductPage productPage = (ProductPage) ApplicationFactory.GetPage(Pages.ProductPage);
        productPage.SelectAddToCart();
        productPage.OpenCartPage();
        CartPage cartPage = (CartPage) ApplicationFactory.GetPage(Pages.CartPage);
        cartPage.SelectQuantityForCartItem(productInfo.Title,quantity);
        cartPage.refresh();
        cartPage = (CartPage) ApplicationFactory.GetPage(Pages.CartPage);
        Assert.assertEquals(cartPage.GetTopCartItemTitle(),productInfo.Title);
        ReportLoggerFactory.AddPass(" Product title in cart verified");
        Assert.assertEquals(cartPage.GetTopCartItemPrice(),productInfo.Price);
        ReportLoggerFactory.AddPass(" Product price in cart verified");
        Assert.assertEquals(cartPage.GetSubTotalValue(),
                DataConverters.GetProductOfValues(productInfo.Price,quantity));
        ReportLoggerFactory.AddPass(" Subtotal price with respect to quantity verified");
    }
}

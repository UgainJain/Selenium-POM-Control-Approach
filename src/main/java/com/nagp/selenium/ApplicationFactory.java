package com.nagp.selenium;

import com.nagp.Shared.Pages;
import com.nagp.selenium.Base.CustomPageFactory.PageFactoryWrapper.PageElementFactory;
import com.nagp.selenium.PageObjects.CartPage;
import com.nagp.selenium.PageObjects.HomePage;
import com.nagp.selenium.PageObjects.IPage;
import com.nagp.selenium.PageObjects.ProductPage;
import org.openqa.selenium.WebDriver;

public final class ApplicationFactory {
    private static WebDriver driver;

    // PageObject getter on the basis of Pages enum
    public static IPage GetPage(Pages page)
    {
        IPage iPage ;
        switch (page)
        {
            case HomePage:
                iPage  = new PageElementFactory().initElements(driver, HomePage.class);
                break;
            case ProductPage:
                iPage  = new PageElementFactory().initElements(driver, ProductPage.class);
                break;
            case CartPage:
                iPage  = new PageElementFactory().initElements(driver, CartPage.class);
                break;
            default:
                iPage = null;
                break;
        }
        return iPage;
    }

    public static void setDriver(WebDriver driver) {
        ApplicationFactory.driver = driver;
    }
}

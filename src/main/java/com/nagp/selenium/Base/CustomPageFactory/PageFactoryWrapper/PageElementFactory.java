package com.nagp.selenium.Base.CustomPageFactory.PageFactoryWrapper;

import java.lang.reflect.InvocationTargetException;
import com.nagp.selenium.Base.CustomPageFactory.CustomElementLocatorFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.nagp.selenium.Base.CustomPageFactory.WebElementDecorator.WebElementDecorator;

public class PageElementFactory extends PageFactory{

    /**
     *  Initializes a page factory from a class with a template of Elements.
     * @param driver - webdriver
     * @param pageClass - page oject class
     * @param <T> - control type
     * @return -  control type
     */
    public static <T> T initElements(WebDriver driver, Class<T> pageClass) {
        try {
            T page = pageClass.getConstructor(WebDriver.class).newInstance(driver);
            PageFactory.initElements(
                new WebElementDecorator(
                    new CustomElementLocatorFactory(driver)), page);
            return page;
        } catch (InstantiationException | IllegalAccessException            
            | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
	
}

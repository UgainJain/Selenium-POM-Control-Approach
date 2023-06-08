package com.nagp.selenium.Base.CustomPageFactory.ElementLocator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

import java.lang.reflect.Field;

/**
 * Custom ElementLocator extending DefaultElementLocator for @FindBy
 */

public class CustomElementLocator extends DefaultElementLocator {
    public CustomElementLocator(SearchContext searchContext, Field field) {
        super(searchContext, field);
    }
}

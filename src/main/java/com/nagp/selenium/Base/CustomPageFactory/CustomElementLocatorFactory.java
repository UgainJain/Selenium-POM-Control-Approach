package com.nagp.selenium.Base.CustomPageFactory;

import com.nagp.selenium.Base.CustomPageFactory.ElementLocator.CustomElementLocator;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

//Custo ElementLocator Factory extending ElementLocatorFactory for @FindBy
public class CustomElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext searchContext;
    public CustomElementLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }
    public ElementLocator createLocator(Field field) {
        return new CustomElementLocator(searchContext, field);
    }
}

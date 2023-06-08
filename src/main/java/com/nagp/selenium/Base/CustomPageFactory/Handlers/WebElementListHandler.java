package com.nagp.selenium.Base.CustomPageFactory.Handlers;

import static com.nagp.selenium.Base.CustomPageFactory.Converters.AnnotationConverter.getWrapperTypeClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.nagp.selenium.Controls.Controls.IWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.nagp.selenium.Controls.Concrete.WebElementWrapped;

public class WebElementListHandler implements InvocationHandler{
	private final ElementLocator elementLocator;
	private final Class<?> type	;
    /**
     * Handler for Element of custom type control in lists
     * @param interfaceType - control type
     * @param locator - element Locator
     * @param <T> - Interface control type
     */
	public <T> WebElementListHandler(Class<T> interfaceType, ElementLocator locator) {
        this.elementLocator = locator;
        if (!IWebElement.class.isAssignableFrom(interfaceType)) {
            throw new RuntimeException("interface not assignable to Element.");
        }
        this.type = getWrapperTypeClass(interfaceType);

    }
    /**
     * invoker for the Control object
     * @param proxy - proxy elements
     * @param method - invoker method
     * @param objectsList - objects
     * @return - control type object converted to interface lists
     * @throws Throwable - custom exception to for Element not found
     */
	@Override
	public Object invoke(Object proxy, Method method, Object[] objectsList) throws Throwable {
		List<Object> wrappedList = new ArrayList<Object>();
        Constructor<?> cons = type.getConstructor(WebElement.class);
        for (WebElement element : elementLocator.findElements()) {
            Object thing = cons.newInstance(element);
            wrappedList.add(type.cast(thing));
        }
        try {
            return method.invoke(wrappedList, objectsList);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
	}
}

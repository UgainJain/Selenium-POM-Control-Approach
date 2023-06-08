package com.nagp.selenium.Base.CustomPageFactory.Handlers;

import static com.nagp.selenium.Base.CustomPageFactory.Converters.AnnotationConverter.getWrapperTypeClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.nagp.selenium.Controls.Controls.IWebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class WebElementHandler implements InvocationHandler{
	private final ElementLocator elementLocator;
	private final Class<?> type	;

    /**
     * Handler for Element of custom type control
     * @param interfaceType - control type
     * @param locator - element Locator
     * @param <T> - Interface control type
     */
	public <T> WebElementHandler(Class<T> interfaceType, ElementLocator locator) {
        this.elementLocator = locator;
        if (!IWebElement.class.isAssignableFrom(interfaceType)) {
            throw new RuntimeException("interface not assignable to Element.");
        }
        this.type = getWrapperTypeClass(interfaceType);
    }

    /**
     * invoker for the Control object
     * @param arg0
     * @param method - invoker method
     * @param objectList - objects
     * @return - control type object converted to interface
     * @throws Throwable - custom exception to for Element not found
     */
	@Override
	public Object invoke(Object arg0, Method method, Object[] objectList) throws Throwable {
        final WebElement element;
        try {
            element = elementLocator.findElement();
        } catch (NoSuchElementException e) {
            if ("toString".equals(method.getName())) {
                return "Proxy element for: " + elementLocator.toString();
            }
            throw new Exception("Element not found by "+ elementLocator.toString());
        }

        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }
        Constructor<?> cons = type.getConstructor(WebElement.class);
        Object thing = cons.newInstance(element);
        try {
            return method.invoke(type.cast(thing), objectList);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
	}
}

package com.nagp.selenium.Base.CustomPageFactory.WebElementDecorator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.FindBy;
import com.nagp.selenium.Controls.Controls.IWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import com.nagp.selenium.Controls.Concrete.WebElementWrapped;
import com.nagp.selenium.Base.CustomPageFactory.Handlers.WebElementHandler;
import com.nagp.selenium.Base.CustomPageFactory.Handlers.WebElementListHandler;

public class WebElementDecorator implements FieldDecorator {

	protected ElementLocatorFactory _elementLocatorFactory; 
	public WebElementDecorator (ElementLocatorFactory elementLocatorFactory)
	{
		_elementLocatorFactory = elementLocatorFactory;
	}

	/**
	 * Decorator for control element using whether the control type s assignable or not
	 * @param classLoader
	 * @param controlType
	 * @return
	 */
	@Override
	public Object decorate(ClassLoader classLoader, Field controlType) {
		if(!(IWebElement.class.isAssignableFrom(controlType.getType())
			|| isDecoratableTypeList(controlType)))
		{
			return null;
		}
		ElementLocator elementLocator = _elementLocatorFactory.createLocator(controlType);
		if(elementLocator == null)
			return null;
		Class<?> fieldType = controlType.getType();
		if(IWebElement.class.equals(fieldType))
			fieldType = WebElementWrapped.class;
		if(IWebElement.class.isAssignableFrom(fieldType))
			return proxyForLocator(classLoader,fieldType, elementLocator );
		else if(List.class.isAssignableFrom(fieldType))
		{
			Class erasureType = (Class)getErasureOfType(controlType)[0];
			return proxyForListLocator(classLoader, erasureType, elementLocator);
		}
		return null;
	}

	// get type element for the control
	private Type[] getErasureOfType(Field controlType) {
        Type genericType = controlType.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return null;
        }
        return ((ParameterizedType)genericType).getActualTypeArguments();
    }
    // For Object of type List
	private boolean isDecoratableTypeList(Field controlType) {
        if (!List.class.isAssignableFrom(controlType.getType())) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        Class erasureType = (Class)getErasureOfType(controlType)[0];
        if (erasureType == null || !IWebElement.class.isAssignableFrom(erasureType)) {
            return false;
        }
        if (controlType.getAnnotation(FindBy.class) == null && controlType.getAnnotation(FindBys.class) == null) {
            return false;
        }
        return true;
    }
	/* Generate a type-parameterized locator proxy for the element in question. */
    protected <T> T proxyForLocator(ClassLoader classLoader, Class<T> interfaceType, ElementLocator locator) {
        InvocationHandler elementHandler = new WebElementHandler(interfaceType, locator);
        T proxy;
        proxy = interfaceType.cast(Proxy.newProxyInstance(
        		classLoader, new Class[]{interfaceType, WebElement.class, WrapsElement.class, Locatable.class}, elementHandler));
        return proxy;
    }
    // Proxy generator for object of type list
    @SuppressWarnings("unchecked" )
	protected <T> List<T> proxyForListLocator(ClassLoader loader, Class<T> controlTypeInterface, ElementLocator elementLocator) {
        InvocationHandler handler = new WebElementListHandler(controlTypeInterface, elementLocator);

        List<T> proxy;
        proxy = (List<T>) Proxy.newProxyInstance(
                loader, new Class[]{List.class}, handler);
        return proxy;
    }
}

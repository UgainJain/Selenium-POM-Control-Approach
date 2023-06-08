package com.nagp.selenium.Base.CustomPageFactory.Converters;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.WebElementWrapped;

/**
 * Annotation converter for @ImplementedIn annotation
 */
public final class AnnotationConverter 
{
	public static <T> Class<?> getWrapperTypeClass(Class<T> iface) {
        if (iface.isAnnotationPresent(ImplementedIn.class)) {
        	ImplementedIn annotation = iface.getAnnotation(ImplementedIn.class);
            Class<?> clazz = annotation.value();
            if (WebElementWrapped.class.isAssignableFrom(clazz)) {
                return annotation.value();
            }
        }
        throw new UnsupportedOperationException("Apply @ImplementedIn annotation to your Interface " + 
                iface.getCanonicalName() + " if you want to extend ");
    }


}

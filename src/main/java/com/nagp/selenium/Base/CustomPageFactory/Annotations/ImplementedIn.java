package com.nagp.selenium.Base.CustomPageFactory.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.nagp.selenium.Controls.Concrete.WebElementWrapped;

/**
 * Annotation to get the implemented class for the specific interface
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImplementedIn {

    Class<?> value() default WebElementWrapped.class;

}

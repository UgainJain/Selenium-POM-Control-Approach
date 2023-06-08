package com.nagp.selenium.Base.CustomPageFactory.Annotations;

import com.nagp.selenium.Utils.LoadProperty;
import org.openqa.selenium.By;
import org.openqa.selenium.support.AbstractFindByBuilder;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.pagefactory.ByAll;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;


/**
 * Custom FindAll To get the locator from the properties file
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@PageFactoryFinder(FindAll.CustomFindByImplementation.class)
public @interface FindAll {
    FindBy[] value();

    public static class CustomFindByImplementation extends AbstractFindByBuilder {
        /**
         * @param field expected to be an element in a Page Object
         */
        public By buildIt(Object annotation, Field field) {
            FindAll findBys = (FindAll) annotation;

            FindBy[] findByArray = findBys.value();
            By[] byArray = new By[findByArray.length];
            for (int i = 0; i < findByArray.length; i++) {
                byArray[i] = buildByFromFindBy(findByArray[i]);
            }

            return new ByAll(byArray);
        }

        protected By buildByFromShortFindBy(FindBy findBy) {
            if (!"".equals(findBy.className())) {
                return By.className(GetLocator(findBy.className()));
            }

            if (!"".equals(findBy.css())) {
                return By.cssSelector(GetLocator(findBy.css()));
            }

            if (!"".equals(findBy.id())) {
                return By.id(GetLocator(findBy.id()));
            }

            if (!"".equals(findBy.linkText())) {
                return By.linkText(GetLocator(findBy.linkText()));
            }

            if (!"".equals(findBy.name())) {
                return By.name(GetLocator(findBy.name()));
            }

            if (!"".equals(findBy.partialLinkText())) {
                return By.partialLinkText(GetLocator(findBy.partialLinkText()));
            }

            if (!"".equals(findBy.tagName())) {
                return By.tagName(GetLocator(findBy.tagName()));
            }

            if (!"".equals(findBy.xpath())) {
                return By.xpath(GetLocator(findBy.xpath()));
            }

            // Fall through
            return null;
        }
        protected By buildByFromLongFindBy(com.nagp.selenium.Base.CustomPageFactory.Annotations.FindBy findBy) {
            String using = GetLocator(findBy.using());
            return findBy.how().buildBy(using);
        }
        protected By buildByFromFindBy(com.nagp.selenium.Base.CustomPageFactory.Annotations.FindBy findBy) {

            By ans = buildByFromShortFindBy(findBy);
            if (ans == null) {
                ans = buildByFromLongFindBy(findBy);
            }

            return ans;
        }
        private String GetLocator(String using)
        {
            return LoadProperty.getValueForConstant(using);
        }
    }

}

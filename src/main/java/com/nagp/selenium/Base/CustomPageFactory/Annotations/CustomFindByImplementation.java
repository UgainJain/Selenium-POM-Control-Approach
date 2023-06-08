package com.nagp.selenium.Base.CustomPageFactory.Annotations;

import com.nagp.selenium.Utils.LoadProperty;
import org.openqa.selenium.By;
import org.openqa.selenium.support.AbstractFindByBuilder;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.How;

import java.lang.reflect.Field;


//
//stored here only for reference as used in FindBy and find all implementation

public abstract class CustomFindByImplementation extends AbstractFindByBuilder {
    /**
     * @param field expected to be an element in a Page Object
     */
    public By buildIt(Object annotation, Field field) {
        org.openqa.selenium.support.FindBy findBy = (org.openqa.selenium.support.FindBy) annotation;
        assertValidFindBy(findBy);

        By ans = buildByFromShortFindBy(findBy);
        if (ans == null) {
            ans = buildByFromLongFindBy(findBy);
        }

        return ans;

    }
    @Override
    protected By buildByFromShortFindBy(org.openqa.selenium.support.FindBy findBy) {
        How how = findBy.how();
        String using = findBy.using();
        String elementLocator = LoadProperty.getValueForConstant(using);
        switch (how) {
            case CLASS_NAME:
                return By.className(elementLocator);
            case ID:
                return By.id(elementLocator);
            case ID_OR_NAME:
                return new ByIdOrName(elementLocator);
            case LINK_TEXT:
                return By.linkText(elementLocator);
            case NAME:
                return By.name(elementLocator);
            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(elementLocator);
            case TAG_NAME:
                return By.tagName(elementLocator);
            case XPATH:
                return By.xpath(elementLocator);
            default:
                throw new IllegalArgumentException("Cannot determine how to locate element ");
        }

    }
    @Override
    protected By buildByFromLongFindBy(org.openqa.selenium.support.FindBy findBy) {
        String using = findBy.using();
        String elementLocator = LoadProperty.getValueForConstant(using);
        return findBy.how().buildBy(elementLocator);
    }
    protected By buildByFromFindBy(org.openqa.selenium.support.FindBy findBy) {
        assertValidFindBy(findBy);

        By ans = buildByFromShortFindBy(findBy);
        if (ans == null) {
            ans = buildByFromLongFindBy(findBy);
        }

        return ans;
    }
}

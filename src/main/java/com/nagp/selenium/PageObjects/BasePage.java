package com.nagp.selenium.PageObjects;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.FindBy;
import com.nagp.selenium.Constants.ElementLocators;
import com.nagp.selenium.Controls.Controls.ILink;
import com.nagp.selenium.Controls.Controls.ISearchBar;
import com.nagp.selenium.DataFactory.ReportLoggerFactory;
import org.openqa.selenium.WebDriver;

// Base Page for all the PageObjects
public class BasePage implements IPage {
    protected WebDriver webDriver ;

    public BasePage(WebDriver _webDriver )
    {
        webDriver = _webDriver;
    }


    @FindBy(id = ElementLocators.NavCart)
    protected ILink NavCartButton;
    @FindBy(id = ElementLocators.SearchBar)
    protected ISearchBar SearchBar;

    public void Search(String value){
        SearchBar.Search(value);
        ReportLoggerFactory.AddInfo("Searching "+value + " in nav bar");
    }
    @Override
    public void refresh() {
        webDriver.navigate().refresh();
    }

}

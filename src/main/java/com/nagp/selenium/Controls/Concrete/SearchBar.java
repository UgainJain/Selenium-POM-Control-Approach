package com.nagp.selenium.Controls.Concrete;

import com.nagp.selenium.Controls.Controls.IButton;
import com.nagp.selenium.Controls.Controls.ISearchBar;
import com.nagp.selenium.Controls.Controls.ITextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//SearchBar control implementation
public class SearchBar extends WebElementWrapped implements ISearchBar {
    private ITextBox searchTextBox;
    private IButton searchButton;
    public SearchBar(WebElement element) {
        super(element);
        searchTextBox = new TextBox(element.findElement(By.cssSelector("input[type='text']")));
        searchButton = new Button(element.findElement(By.cssSelector("input[type='submit']")));
    }
    @Override
    public void Search(String value) {
        searchTextBox.SetText(value);
        searchButton.Click();
    }
}

package com.nagp.selenium.Controls.Controls;

import com.nagp.selenium.Base.CustomPageFactory.Annotations.ImplementedIn;
import com.nagp.selenium.Controls.Concrete.SearchBar;


// Control for type SearchBar
@ImplementedIn(SearchBar.class)
public interface ISearchBar extends IWebElement{
    void Search(String value);
}

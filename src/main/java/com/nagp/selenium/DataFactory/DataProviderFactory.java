package com.nagp.selenium.DataFactory;

import com.nagp.selenium.Constants.TestDataConstants;
import com.nagp.selenium.Utils.LoadProperty;
import org.testng.annotations.DataProvider;

// Data providers for each test cases
public class DataProviderFactory {

    @DataProvider(name = "BasicSearch")
    public Object[] BasicSearch()
    {
        return new Object[] {LoadProperty.getValueForConstant(TestDataConstants.BasicSearchQuery)};
    }
    @DataProvider(name = "SearchWithRating")
    public Object[][] SearchWithRating()
    {
        return new Object[][] {{LoadProperty.getValueForConstant(TestDataConstants.BasicSearchQuery),
                LoadProperty.getValueForConstant(TestDataConstants.Rating)}};
    }
    @DataProvider(name = "SearchWithRatingWithPrice")
    public Object[][] SearchWithRatingWithPrice()
    {
        return new Object[][] {{LoadProperty.getValueForConstant(TestDataConstants.BasicSearchQuery),
                LoadProperty.getValueForConstant(TestDataConstants.Price),
                LoadProperty.getValueForConstant(TestDataConstants.Rating)}};
    }
    @DataProvider(name = "SearchWithPrice")
    public Object[][] SearchWithPrice()
    {
        return new Object[][] {{LoadProperty.getValueForConstant(TestDataConstants.BasicSearchQuery),
                LoadProperty.getValueForConstant(TestDataConstants.Price)}};
    }
    @DataProvider(name = "SearchWithQuantity")
    public Object[][] SearchWithQuantity()
    {
        return new Object[][] {{LoadProperty.getValueForConstant(TestDataConstants.BasicSearchQuery),
                DataConverters.GetIntegerValue(LoadProperty.getValueForConstant(TestDataConstants.Quantity))}};
    }
}

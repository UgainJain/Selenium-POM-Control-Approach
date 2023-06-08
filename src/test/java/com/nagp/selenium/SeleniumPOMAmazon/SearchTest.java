package com.nagp.selenium.SeleniumPOMAmazon;

import com.nagp.selenium.ApplicationFactory;
import com.nagp.selenium.DataFactory.DataConverters;
import com.nagp.selenium.DataFactory.DataProviderFactory;
import com.nagp.selenium.DataFactory.ReportLoggerFactory;
import com.nagp.selenium.PageObjects.HomePage;
import com.nagp.Shared.Pages;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SearchTest extends BaseTest{

    @Test(testName = "Simple search to verify top result.", dataProvider = "BasicSearch", dataProviderClass = DataProviderFactory.class)
    public void simpleSearchTest(String searchQuery)
    {
        HomePage homePage = (HomePage)ApplicationFactory.GetPage(Pages.HomePage);
        homePage.Search(searchQuery);
        Assert.assertTrue(DataConverters.ContainsWithCaseInsensitive(homePage.GetTopResultTitle(),searchQuery));
        ReportLoggerFactory.AddPass(searchQuery+ " found in top result");
    }
    @Test(testName = "Simple search with rating filter and verify all result.", dataProvider = "SearchWithRating", dataProviderClass = DataProviderFactory.class)
    public void simpleSearchTestWithRatingFilter(String searchQuery,String rating)
    {
        HomePage homePage = (HomePage)ApplicationFactory.GetPage(Pages.HomePage);
        homePage.Search(searchQuery);
        homePage.SelectRatingsFilter(rating);
        Assert.assertTrue(homePage.GetAllCardsRatings().stream()
                .allMatch(x->x >= DataConverters.GetFirstFloatFromString(rating)));
        ReportLoggerFactory.AddPass(rating+ " filter applied in all results");
    }
    @Test(testName = "Simple search with price filter and verify all result.", dataProvider = "SearchWithPrice", dataProviderClass = DataProviderFactory.class)
    public void simpleSearchTestWithPriceFilter(String searchQuery,String price)
    {
        HomePage homePage = (HomePage)ApplicationFactory.GetPage(Pages.HomePage);
        homePage.Search(searchQuery);
        homePage.SelectPriceFilter(price);
        List<Integer> priceRange = DataConverters.GetAllIntegersFromString(price);
        Assert.assertTrue(homePage.GetAllCardsPrice().stream()
                .allMatch(x->x >= DataConverters.GetLowerValue(priceRange) && x <= DataConverters.GetUpperValue(priceRange)));
        ReportLoggerFactory.AddPass(price+ " filter applied in all results");
    }
    @Test(testName = "Simple search with price filter combined with rating filter and verify all result.", dataProvider = "SearchWithRatingWithPrice", dataProviderClass = DataProviderFactory.class)
    public void simpleSearchTestWithPriceAndRatingFilter(String searchQuery,String price,String rating)
    {
        HomePage homePage = (HomePage)ApplicationFactory.GetPage(Pages.HomePage);
        homePage.Search(searchQuery);
        homePage.SelectPriceFilter(price);
        homePage.SelectRatingsFilter(rating);
        List<Integer> priceRange = DataConverters.GetAllIntegersFromString(price);
        Assert.assertTrue(homePage.GetAllCardsRatings().stream()
                .allMatch(x->x >= DataConverters.GetFirstFloatFromString(rating)));
        ReportLoggerFactory.AddPass(rating+ " filter applied in all results");
        Assert.assertTrue(homePage.GetAllCardsPrice().stream()
                .allMatch(x->x >= DataConverters.GetLowerValue(priceRange) && x <= DataConverters.GetUpperValue(priceRange)));
        ReportLoggerFactory.AddPass(price+ " filter applied in all results");

    }
}

package com.nagp.selenium.PageObjects;

import com.nagp.Shared.Entitites.ProductInfo;
import com.nagp.Shared.Extentions.WebDriverExtension;
import com.nagp.selenium.Base.CustomPageFactory.Annotations.FindBy;
import com.nagp.selenium.Constants.ElementLocators;
import com.nagp.selenium.Controls.Controls.IAmazonDataCelGrid;
import com.nagp.selenium.Controls.Controls.ILink;
import com.nagp.selenium.DataFactory.ReportLoggerFactory;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

//Amazon Home page
public class HomePage extends BasePage {

	@FindBy(css = ElementLocators.ResultsGrid)
	private IAmazonDataCelGrid ResultsGrid;
	@FindBy (css = ElementLocators.RatingFilter)
	private List<ILink> RatingFilters;
	@FindBy (css = ElementLocators.PriceFilter)
	private List<ILink> PriceFilters;

	public HomePage(WebDriver driver) {
	    super(driver);
    }

	public void SelectPriceFilter(String value){
		PriceFilters.stream()
				.filter(x->x.GetText().contains(value))
				.findFirst().orElse(null).Click();
		ReportLoggerFactory.AddInfo("Applying price filter with value :"+value );
	}
	public void SelectRatingsFilter(String value){
		RatingFilters.stream()
				.filter(x->x.GetLabel().contains(value))
				.findFirst().orElse(null).Click();
		ReportLoggerFactory.AddInfo("Applying ratings filter with value :"+value );
	}
	public List<Float> GetAllCardsRatings()
	{
		return ResultsGrid.getAllCardsWithoutAds()
				.stream().filter(item->item.GetRating()>0)
				.map(x->x.GetRating())
				.collect(Collectors.toList());
	}
	public List<Float> GetAllCardsPrice()
	{
		return ResultsGrid.getAllCardsWithoutAds()
				.stream().filter(item->item.GetCost()>0)
				.map(x->x.GetCost())
				.collect(Collectors.toList());
	}
	public String GetTopResultTitle()
	{
		return ResultsGrid.getTopResult()
				.GetTitle();
	}
	public ProductInfo GetTopResultProductInfo()
	{
		return new ProductInfo(ResultsGrid.getTopResult());
	}
	public void OpenProductPage()
	{
		WebDriverExtension.OpenUrlInNewTab(webDriver,ResultsGrid.getTopResult().GetUrlLink());
		ReportLoggerFactory.AddInfo("Navigating to product page");
	}
}

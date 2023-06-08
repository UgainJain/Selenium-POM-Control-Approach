package com.nagp.selenium.Driver;

import com.nagp.selenium.DataFactory.DataConverters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.nagp.selenium.Utils.LoadProperty;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import java.util.concurrent.TimeUnit;
import com.nagp.selenium.Constants.ConfigConstants;

public final class Driver {
	private String url;
	private String browser ;
	public Driver(){
		this.url = LoadProperty.getValueForConstant(ConfigConstants.URL);
		this.browser = LoadProperty.getValueForConstant(ConfigConstants.Browser);
	}

	
	/**
	 * This method is used to initialize the WebDriver object
	 */
	public WebDriver invokeDriver() {
		try {
			WebDriver driver = null;
			if (this.browser.equalsIgnoreCase("Firefox")) {
				System.setProperty(
						GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,
						System.getProperty("user.dir")
								+ "/Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty(
						ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
						System.getProperty("user.dir")
								+ "/Drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty(
						InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,
						System.getProperty("user.dir")
								+ "/Drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			if(driver != null)
				driver.manage().timeouts().implicitlyWait(
						DataConverters.GetIntegerValue(LoadProperty.getValueForConstant(ConfigConstants.ImplicitTimeout)),
						TimeUnit.SECONDS);
			return driver;

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to maximize the browser and open the given URL
	 */
	public void openURL(WebDriver driver) {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
	}
}

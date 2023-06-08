package com.nagp.selenium.Managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nagp.selenium.Utils.LoadProperty;
import java.util.Date;

import com.nagp.selenium.Constants.ConfigConstants;

// Extent Report Manager for managing reports
public class ExtentReportManager{

    private static ExtentReports extentReports = null;
    private static ExtentSparkReporter extentReporter;
    private static String reportPath = null;

    public static ExtentReports getInstance(){
        if(extentReports == null) {
            extentReports = createInstance();
        }
        return extentReports;
    }
    // creating instance Of Extent Reports
    public static ExtentReports createInstance() {
        String timeStamp;
        Date d = new Date();
        timeStamp = d.toString().replace(":", "_").replace(" ", "_");
        reportPath = LoadProperty.getValueForConstant(ConfigConstants.TestReportFolderPath);
        String reportFolderPath = System.getProperty("user.dir") + reportPath + "//Test_Report_";
        String filename = timeStamp + ".html";
        extentReporter = new ExtentSparkReporter(reportFolderPath + filename);
        extentReports = new ExtentReports();
        extentReporter.config().setTheme(Theme.STANDARD);
        extentReports.attachReporter(extentReporter);
        //extent.loadConfig(new File(System.getProperty("user.dir") + "//Resources//ReportsConfig.xml"));
        extentReports.setSystemInfo("Selenium version", "3.141.59");
        return extentReports;
    }
}

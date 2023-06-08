package com.nagp.selenium.Listeners;

import com.nagp.selenium.Constants.ConfigConstants;
import com.nagp.selenium.Managers.ExtentReportManager;
import com.nagp.selenium.Managers.ExtentTestManager;
import com.nagp.selenium.Managers.FileManager;
import com.nagp.selenium.Utils.LoadProperty;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.IOException;

//suite listeners for before and after suite working
public class SuiteListeners implements ISuiteListener {
    public static boolean flag = true;
    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("Running Suite : " +iSuite.getName());
        try {
            if(flag) {
                String archivedScreenShot = LoadProperty.getValueForConstant(ConfigConstants.ArchivedTestScreenShotFolderPath);
                String archivedReport = LoadProperty.getValueForConstant(ConfigConstants.ArchivedTestReportFolderPath);
                String currentScreenShot = LoadProperty.getValueForConstant(ConfigConstants.ScreenShotFolderPath);
                String currentReport = LoadProperty.getValueForConstant(ConfigConstants.TestReportFolderPath);
                FileManager.MoveAllContentsToAnotherDirectory(currentScreenShot, archivedScreenShot);
                FileManager.MoveAllContentsToAnotherDirectory(currentReport, archivedReport);
                ExtentReportManager.getInstance();
                flag = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ISuite iSuite) {
        ExtentTestManager.endTestReporting();
        System.out.println("Ending Suite : " +iSuite.getName());
    }
}

package com.nagp.selenium.Managers;

import com.nagp.selenium.Constants.ConfigConstants;
import com.nagp.selenium.Utils.LoadProperty;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

//Screenshot manager to take screenshots
public class ScreenShotManager {
    // Takes screenshots and returns path

    public static String CaptureScreenShot(String imageName, WebDriver driver) throws IOException {
        String screenShotPath = LoadProperty.getValueForConstant(ConfigConstants.ScreenShotFolderPath);
        String screenshotFilePath = System.getProperty("user.dir") + screenShotPath +"/"+ imageName;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(screenshotFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
        return screenshotFilePath;
    }
}

package com.nagp.selenium.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class LoadProperty {
	private static String lastOpenedFile = null;
	private static Properties props =  null;

	private static Properties LoadProperties(String file){
		if(lastOpenedFile!= null && lastOpenedFile.equals(file))
			return props;
		String path = System.getProperty("user.dir");
		props = new Properties();
		InputStreamReader reader = null ;
		try {
			// load a properties file
			path = path + "/Resources/";
			lastOpenedFile = file;
			props = new Properties();
			if(file.equals("testData"))
			{
				reader = new InputStreamReader(new FileInputStream(path + "testData.properties"), "UTF-8");
			}
			else if (file.equals("element"))
			{
				reader = new InputStreamReader(new FileInputStream(path + "elementLocators.properties"),"UTF-8");
			}
			else if (file.equals("config"))
			{
				reader = new InputStreamReader(new FileInputStream(path + "config.properties"),"UTF-8");
			}

			props.load( reader);
			return props;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static String getValue(String key, Properties props)
	{
		if (key != null) {
			String var = props.getProperty(key);
			return var;
		}
		else
		{
			System.out.println("Got null value in key, you might have provided a null value while calling getVar(String key)");
			return null;
		}
	}
	public static String getValueForConstant(String constantKey)
	{
		String[] constantsSplit = constantKey.split("::");
		Properties props = LoadProperties(constantsSplit[0]);
		return getValue(constantsSplit[1], props);
	}
}
package com.flipkart.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class PropertiesUtil 
{
	public static Properties properties;
	
	public static String getProperty(String key)
	{
		return properties.getProperty(key);
	}
	
	static
	{
		try
		{
			properties = new Properties();
			File testProperties = null;
			File locatorProperties = null;
			if (System.getProperty("user.dir") != null)
			{
				testProperties  = new File (System.getProperty("user.dir") + File.separator + "src" + File.separator
						+ "main" + File.separator + "resources" + File.separator + "test.properties");
				
				locatorProperties = new File (System.getProperty("user.dir") + File.separator + "src" + File.separator
						+ "main" + File.separator + "resources" + File.separator + "locators.properties");
			}
			properties.load(new FileInputStream(testProperties));
			properties.load(new FileInputStream(locatorProperties));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}

package com.ibm.mgb.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigureProperties {
	
	public static String getQueryDetails(String key) {
		String value = "";
		try {
			Properties prop = new Properties();
			String path = System.getProperty("user.dir");
			String fpath = path + "\\resources\\query.properties";
			System.out.println(fpath);
			FileInputStream inStream = new FileInputStream(fpath);
			prop.load(inStream);
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.getStackTrace();
		}
		return value;
	}
}

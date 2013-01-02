package com.kousikraj.bootstrap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class is a utility class that is used to load any properties file from
 * the system and to convert that to {@link Properties} object.
 * 
 * @author KousikRaj
 * 
 */
public class PropertiesFileLoader {
	private static Logger	log	= Logger.getLogger(PropertiesFileLoader.class);

	/**
	 * This method is used to load the properties from the specified file name and path and return that as {@link Properties} Object.
	 * @param fileName
	 * @return {@link Properties}
	 */
	public static Properties loadPropertiesFromFile(String fileName) {
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(fileName));
			properties.load(fileInputStream);
			return properties;
		} catch (Exception e) {
			log.error("Error loading properties file: " + fileName, e);
		}
		return properties;
	}

}

package com.kousikraj.bootstrap.configurations;

import java.io.File;

/**
 * 
 * This class is meant to keep all Server / Project related configuration
 * parameters handy for the program to access at run time.
 * 
 * @author KousikRaj
 * 
 * 
 */
public class ServerConfigurations {

	/**
	 * This variable is meant to keep the absolute path of bootstrap.properties
	 * file that contains the properites data related to bootstrap
	 */
	public static String	BOOTSTRAP_CONFIGFILE_PATH;

	public static String	LOG_PROPERTIES;

	/**
	 * In static method, we are getting the folder path of the project and then
	 * getting the config directory where our bootstrap.properties file is
	 * stoored.
	 */
	static {
		File projDir = new File("");
		BOOTSTRAP_CONFIGFILE_PATH = projDir.getAbsolutePath() + File.separator + "config" + File.separator + "bootstrap.properties";
		LOG_PROPERTIES = projDir.getAbsolutePath() + File.separator + "config" + File.separator + "log4j.properties";
	}

}
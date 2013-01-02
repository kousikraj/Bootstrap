package com.kousikraj.bootstrap.lookups;

import org.apache.log4j.Logger;

import com.kousikraj.bootstrap.core.BootstrapConfigurations;
import com.kousikraj.bootstrap.services.Service;

/**
 * This class is a lookup utility where it uses Java Reflection to initialize
 * the object that are specified in {@link BootstrapConfigurations} (loaded from
 * bootstrap.properties file).
 * 
 * @author KousikRaj
 * 
 */
public class ServiceLookup {
	// Logger object to log messages/errors
	private static Logger	log	= Logger.getLogger(ServiceLookup.class);

	/**
	 * This method is used to get initialize the object dynamically using Java
	 * Reflection. This gets the serviceName as argument which is a class path basically. See bootstrap.properties.
	 * 
	 * @param serviceName
	 * @return {@link Service}
	 */
	public static Service getService(String serviceName) {
		Service service = null;
		try {
			@SuppressWarnings("unchecked")
			Class<Service> serviceClass = (Class<Service>) Class.forName(serviceName);
			service = serviceClass.newInstance();
		} catch (Exception e) {
			log.error(e);
		}
		return service;
	}

}

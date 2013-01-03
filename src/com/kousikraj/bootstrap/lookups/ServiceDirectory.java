package com.kousikraj.bootstrap.lookups;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kousikraj.bootstrap.core.BootstrapConfigurations;
import com.kousikraj.bootstrap.services.Service;

/**
 * This class serves as directory for all the services that are loaded by
 * {@link BootstrapConfigurations}.
 * 
 * @author KousikRaj
 * 
 */
public class ServiceDirectory {

	// Singleton Object reference of ServiceDirectory
	private static ServiceDirectory	_instance	= new ServiceDirectory();
	// Map (Directory) of Services
	private Map<Object, Service>	mapOfService;
	// Logger object to log messages/errors
	private static Logger			log			= Logger.getLogger(ServiceLookup.class);

	/**
	 * The method to get the Singleton object of {@link ServiceDirectory}
	 * 
	 * @return {@link ServiceDirectory}
	 */
	public static ServiceDirectory getInstance() {
		return _instance;
	}

	/**
	 * Initializing all the services. Loading the services in to map of services
	 */
	private ServiceDirectory() {
		synchronized (this) {
			this.mapOfService = new HashMap<Object, Service>();
		}
	}

	/**
	 * The method that is used to load the {@link Service} Object in to the
	 * directory
	 * 
	 * @param serviceName
	 * @return {@link Service}
	 */
	public Service loadService(String serviceName) {
		Service service = this.mapOfService.get(serviceName);
		if (service == null) {
			service = ServiceLookup.getService(serviceName);
			this.mapOfService.put(service.getClass().getSimpleName(), service);
			log.info("Service " + service.getClass().getSimpleName() + " is loaded in to the Directory");
		} else {
			log.error("Service " + service.getClass().getSimpleName() + " is already loaded in to the Directory");
		}
		return service;
	}

	/**
	 * The method that is used to get the {@link Service} Object that are
	 * preloaded to the Directory.
	 * 
	 * @param serviceName
	 * @return {@link Service}
	 */
	public Service getService(String serviceName) {
		return this.mapOfService.get(serviceName);
	}

	/**
	 * This method is to clear / reset all the Services loaded in the map
	 * (Directory) so far..
	 * 
	 * @return boolean
	 */
	public boolean removeAllServices() {
		this.mapOfService.clear();
		return true;
	}

}
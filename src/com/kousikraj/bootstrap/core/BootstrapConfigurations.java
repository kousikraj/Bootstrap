package com.kousikraj.bootstrap.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.kousikraj.bootstrap.configurations.ServerConfigurations;
import com.kousikraj.bootstrap.configurations.ServerState;
import com.kousikraj.bootstrap.lookups.ServiceDirectory;
import com.kousikraj.bootstrap.services.Service;
import com.kousikraj.bootstrap.utils.PropertiesFileLoader;

/**
 * 
 * This is the core Configurator for Bootstrap project. This loads the
 * properties from bootstrap.properties file and then initialize services as
 * specified in the file. This is the utility class where we convert the
 * bootstrap.properties file to BootstrapLoader friendly configurations.
 * 
 * @author KousikRaj
 * 
 */
public class BootstrapConfigurations {

	/**
	 * CURRENT_STATE specifies the BootstrapLoader to start the SERVER / SYSTEM
	 * with its services as mentioned in properties file. Normally it would be
	 * at 'NORMAL'state (Check other states at {@link ServerState}
	 */
	public static ServerState					CURRENT_STATE;

	/**
	 * Array of services that are initialized at startup of BootstrapLoader. The
	 * services are loaded as specified in properties file
	 */
	public static Service[]						CURRENT_STATE_SERVICES;

	// This map is used to store all the services along with the STATE
	private static Map<ServerState, Service[]>	mapOfServices	= new HashMap<ServerState, Service[]>();

	// This map is used to store all the arguments (if specified) from
	// properties file for each and service
	private static Map<Service, Object[]>		serviceArgs		= new HashMap<Service, Object[]>();

	/**
	 * The static method is used to load the services as specified in properties
	 * file.
	 */
	static {
		Properties properties = PropertiesFileLoader.loadPropertiesFromFile(ServerConfigurations.BOOTSTRAP_CONFIGFILE_PATH);
		CURRENT_STATE = ServerState.valueOf(properties.getProperty("CURRENT"));
		String[] statesStr = properties.getProperty("SERVICES").split(",");
		for (String stateStr : statesStr) {
			ServerState serverState = ServerState.valueOf(stateStr);
			String[] servicesStr = properties.getProperty(stateStr).split(",");
			Service[] services = new Service[servicesStr.length];
			int count = 0;
			for (String serviceStr : servicesStr) {
				serviceStr = serviceStr.trim();
				Service service = ServiceDirectory.getInstance().loadService(serviceStr);
				String[] serviceArgsStr = properties.getProperty(serviceStr).split(",");
				serviceArgs.put(service, serviceArgsStr);
				services[count++] = service;
			}
			mapOfServices.put(serverState, services);
		}
		CURRENT_STATE_SERVICES = mapOfServices.get(CURRENT_STATE);
	}

	/**
	 * This method is provided to get the available services for the given
	 * {@link ServerState} parameter.
	 * 
	 * @param serverStates
	 * @return {@link Service}[]
	 */
	public static Service[] getService(ServerState serverStates) {
		if (mapOfServices.containsKey(serverStates)) {
			return mapOfServices.get(serverStates);
		}
		return null;
	}

	/**
	 * THis method is provided to get the arguments specified in properties file
	 * for respective {@link Service}
	 * 
	 * @param service
	 * @return Object
	 */
	public static Object[] getServiceArgs(Service service) {
		if (serviceArgs.containsKey(service)) {
			return serviceArgs.get(service);
		}
		return null;
	}

}

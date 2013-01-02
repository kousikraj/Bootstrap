package com.kousikraj.bootstrap.services;

import com.kousikraj.bootstrap.configurations.ServerState;
import com.kousikraj.bootstrap.core.Bootstrap;

/**
 * This is the primary interface where all the methods needed for Service
 * implementation is defined. See {@link AbstractService} for abstract
 * implementation of Service
 * 
 * @author KousikRaj
 * 
 */
public interface Service {

	/**
	 * To Init the Service with args as argument
	 * @param bootstrapLoader
	 * @param args
	 */
	void init(Bootstrap bootstrapLoader, Object args);

	/**
	 * To Start the Service with args as argument
	 * @param bootstrapLoader
	 * @param args
	 */
	void start(Bootstrap bootstrapLoader, Object args);

	/**
	 * To Reboot the Service with args as argument
	 * @param bootstrapLoader
	 * @param args
	 */
	void reboot(Bootstrap bootstrapLoader, Object args);

	/**
	 * To Stop the Service with args as argument
	 * @param bootstrapLoader
	 * @param args
	 */
	void stop(Bootstrap bootstrapLoader, Object args);

	/**
	 * To Set the current {@link ServiceState}of the Service
	 * @param serviceStates
	 * @param args
	 */
	void setState(ServiceState serviceStates, Object args);

	/**
	 * To get the {@link ServerState} of the Service
	 * @return
	 */
	ServiceState getState();

	/**
	 * To get the {@link ServiceType} of the Service
	 * @return
	 */
	ServiceType getType();

}
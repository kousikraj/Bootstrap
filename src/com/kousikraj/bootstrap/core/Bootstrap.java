package com.kousikraj.bootstrap.core;

import org.apache.log4j.Logger;

import com.kousikraj.bootstrap.configurations.ServerState;
import com.kousikraj.bootstrap.lookups.ServiceDirectory;
import com.kousikraj.bootstrap.services.Service;
import com.kousikraj.bootstrap.services.ServiceState;
import com.kousikraj.bootstrap.services.ServiceType;

/**
 * This {@link Bootstrap} class is the main class where we get the
 * configurations from {@link BootstrapConfigurations} and the start the system
 * state by state sequentially.
 * 
 * This class is made Singleton because any other object related to
 * {@link Bootstrap} will be loaded only when this object is Initialized.
 * 
 * @author KousikRaj
 * 
 */
public class Bootstrap {

	// Logger object to log any messages/errors
	static Logger				log			= Logger.getLogger(Bootstrap.class);

	// Private instance to keep the intialized bootstrap object as Singleton
	private static Bootstrap	_instance	= new Bootstrap();

	private ServerState			serverState;

	/**
	 * Method to get the Singleton object of {@link Bootstrap}.
	 * 
	 * @return {@link Bootstrap}
	 */
	public static Bootstrap getInstance() {
		return _instance;
	}

	private ServerState	currentState;
	private Service[]	currentServices;
	private int			countOfCurrentServices;
	private int			countOfLoadedServices;
	private Service		lastLoadedService;

	/**
	 * Private constructor where we are initializing all the private members of
	 * this class based on properties loaded by {@link BootstrapConfigurations}
	 */
	private Bootstrap() {
		synchronized (this) {
			this.currentState = BootstrapConfigurations.CURRENT_STATE;
			this.currentServices = BootstrapConfigurations.CURRENT_STATE_SERVICES;
			this.countOfCurrentServices = this.currentServices.length;
			this.countOfLoadedServices = 0;
			serverState = ServerState.ERROR;
		}
	}

	/**
	 * The method that initializes the boot of the {@link Bootstrap}. This
	 * method internally calls nextState() method to load the Services
	 * sequentially as specified in bootstrap.properties file
	 */
	public void bootSystem() {
		this.nextState();
	}

	/**
	 * This method iterates recursively and initializes all the services as
	 * specified in bootstrap.properties file sequentially. This starts all the
	 * services only corresponding to CURRENT_STATE Also, internally this
	 * initializes the {@link Service} by invoking init() method by passing the
	 * arguments that are specified in bootstrap.properties file. If any
	 * Essential service is not loaded due to some error, then the SERVER /
	 * SYSTEM will be put to {@link ServerState}.SERVER_SERVICE_ERROR and will
	 * stop system boot and will shutdown automatically. If any Addon service is
	 * not loaded then the flag will be set as ERROR for that service and then
	 * the System will be restricted to access it.
	 */
	private void nextState() {
		Service service = this.currentServices[this.countOfLoadedServices++];
		log.info("Loading " + service.getClass().getName());
		Object[] serviceArgs = BootstrapConfigurations.getServiceArgs(service);
		service.init(this, serviceArgs);
		service.start(this, serviceArgs);
		this.lastLoadedService = service;
		// HALTING SERVER IF ONE OF ANY SERVICE SETS ERROR AT STARTUP
		if (service.getType() == ServiceType.ESSENTIAL && service.getState() == ServiceState.ERROR) {
			this.shutDown();
			this.serverState = ServerState.SERVER_SERVICE_ERROR;
			log.error("Error loading ESSENTIAL Service: " + this.lastLoadedService.getClass().getName() + "\nServer will not start until this error is fixed");
			return;
		} else if (service.getType() == ServiceType.ADDON && service.getState() == ServiceState.ERROR) {
			log.error("Error loading ADDON Service: " + this.lastLoadedService.getClass().getName() + "\nThis may not work until this error is fixed");
		} else {
			log.info("Loaded " + this.lastLoadedService.getClass().getName());
		}
		if (this.countOfCurrentServices > countOfLoadedServices) {
			if (service.getState() != ServiceState.ERROR) {
				this.nextState();
			} else {
				log.error("Error loading service " + service + " in " + currentState + " mode");
			}
		} else {
			serverState = currentState;
			log.info("Loaded all services for " + currentState + " mode");
		}
	}

	/**
	 * This method is for Shutting down all the Services that are started for
	 * the CURRENT_STATE.
	 */
	public void shutDown() {
		Service service = this.currentServices[--this.countOfLoadedServices];
		log.info("Shutting down service " + service.getClass().getName());
		service.stop(this, BootstrapConfigurations.getServiceArgs(service));
		this.lastLoadedService = service;
		log.info("Shutdown compelted for service " + this.lastLoadedService.getClass().getName());
		if (this.countOfLoadedServices > 0) {
			if (service.getState() != ServiceState.ERROR) {
				this.shutDown();
			} else {
				log.error("Error Shutting down service " + service + " in " + currentState + " mode");
			}
		} else {
			this.countOfCurrentServices = 0;
			//removing all services from ServiceDirectory...
			ServiceDirectory.getInstance().removeAllServices();
			log.info("Completed shutting down all services for " + currentState + " mode");
		}
	}

	/**
	 * This method is to return the current state of the SYSTEM/SERVER.
	 * 
	 * @return {@link ServerState}
	 */
	public ServerState getState() {
		return this.serverState;
	}

}

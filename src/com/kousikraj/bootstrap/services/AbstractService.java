package com.kousikraj.bootstrap.services;

import org.apache.log4j.Logger;

import com.kousikraj.bootstrap.core.Bootstrap;

/**
 * This class is Abstract implementation of {@link Service}. This class can be
 * extended by any Custom {@link Service} we are creating.
 * 
 * @author KousikRaj
 * 
 */
public abstract class AbstractService implements Service {

	// This logger object is used to log any messages/error
	protected static Logger	log	= Logger.getLogger(AbstractService.class);

	// Private instance to keep the current state of the Service.
	private ServiceState	currentState;

	// Default Constructor. Initially this service is put to Stopped mode.
	public AbstractService() {
		this.setState(ServiceState.STOPPED, null);
	}

	/**
	 * This method is for initializing any {@link Service}. Here we need to
	 * prepare any service to get ready to be started. The successful invocation
	 * will put the service to {@link ServiceState}.INIT mode.
	 */
	@Override
	public void init(Bootstrap bootstrapLoader, Object args) {
		this.setState(ServiceState.INIT, args);

	}

	/**
	 * This method is for starting the {@link Service} where it will be
	 * available to be accessed. The successful invocation will put the service
	 * to {@link ServiceState} .STARTED mode
	 */
	@Override
	public void start(Bootstrap bootstrapLoader, Object args) {
		this.setState(ServiceState.STARTED, args);

	}

	/**
	 * This method is for rebooting the {@link Service} by invoking stop, init
	 * and start methods sequentially
	 */
	@Override
	public void reboot(Bootstrap bootstrapLoader, Object args) {
		this.stop(bootstrapLoader, args);
		this.init(bootstrapLoader, args);
		this.start(bootstrapLoader, args);
	}

	/**
	 * This method is for stopping the {@link Service} where it will be not be
	 * available to be accessed. The successful invocation will put the service
	 * to {@link ServiceState} .STOPPED mode
	 */
	@Override
	public void stop(Bootstrap bootstrapLoader, Object args) {
		this.setState(ServiceState.STOPPED, args);

	}

	/**
	 * This method is for setting the {@link ServiceState} of the
	 * {@link Service}
	 */
	@Override
	public void setState(ServiceState serviceStates, Object args) {
		this.currentState = serviceStates;

	}

	/**
	 * This method is to get the current {@link ServiceState} of the
	 * {@link Service}
	 */
	@Override
	public ServiceState getState() {
		return this.currentState;
	}

	/**
	 * This method is to get the {@link ServiceType} of the Service. Bt default,
	 * it is considered as {@link ServiceType}.ESSENTIAL service, if it has to
	 * be {@link ServiceType}.ADDON then we need to override this method in the
	 * Service implementation
	 */
	@Override
	public ServiceType getType() {
		return ServiceType.ESSENTIAL;
	}

}

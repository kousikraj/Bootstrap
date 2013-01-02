package com.kousikraj.bootstrap.services;

/**
 * The enum is to define all different states of {@link Service}
 * 
 * @author KousikRaj
 * 
 */
public enum ServiceState {
	
	/**
	 * This value is set for Service after successful invocation of init method of {@link Service}
	 */
	INIT,
	/**
	 * This value is set for Service after successful invocation of start method of {@link Service}
	 */
	STARTED,
	/**
	 * This value is set for Service after successful invocation of stop method of {@link Service}
	 */
	STOPPED,
	/**
	 * This value is set for Service for any error that happens in this {@link Service}
	 */
	ERROR
}

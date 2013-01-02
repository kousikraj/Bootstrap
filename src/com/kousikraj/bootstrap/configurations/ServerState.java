package com.kousikraj.bootstrap.configurations;

/**
 * 
 * This is mainly to manage State of Server / System. This states are defined in
 * bootstrap.properties file where each state will have its own service listings
 * 
 * @author KousikRaj
 * 
 */
public enum ServerState {
	/**
	 * This state should normally be used when the SERVER / SYSTEM is stable or
	 * in normal state
	 */
	NORMAL,
	/**
	 * This state should be used when the SERVER / SYSTEM is to be put on IDLE state.
	 * Some of the scenarios when we have to consider IDLE state is like UPDATE, MAINTENANCE, DISASTER RECOVERY and similar...
	 */
	IDLE,
	/**
	 * This state should be used when the SERVER / SYSTEM is having some error
	 */
	ERROR,
	/**
	 * This state is generally a system generated error where when any of the ESSENTIAL SERVICE is crashed, or 
	 * Bootstrap failed due to some issues. 
	 */
	SERVER_SERVICE_ERROR
}

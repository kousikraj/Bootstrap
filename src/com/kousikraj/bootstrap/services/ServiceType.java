package com.kousikraj.bootstrap.services;

/**
 * This method is used to define all different types of {@link Service}
 * 
 * @author KousikRaj
 * 
 */
public enum ServiceType {
	/**
	 * This value is default {@link ServiceType} for any {@link Service}
	 */
	ESSENTIAL,
	/**
	 * This value has to be defined to any {@link Service} that is not mandatory
	 * for the SERVER / SYSTEM.
	 */
	ADDON
}

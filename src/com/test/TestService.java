package com.test;

import com.kousikraj.bootstrap.core.Bootstrap;
import com.kousikraj.bootstrap.services.AbstractService;
import com.kousikraj.bootstrap.services.ServiceState;

public class TestService extends AbstractService {
	
	@Override
	public void init(Bootstrap bootstrapLoader, Object args) {
		log.info("Initialized TestService from custom package..");
		super.init(bootstrapLoader, args);
	}

	@Override
	public void start(Bootstrap bootstrapLoader, Object args) {
		log.info("Started TestService from custom package..");
		super.start(bootstrapLoader, args);
	}

	@Override
	public void stop(Bootstrap bootstrapLoader, Object args) {
		log.info("Stopped TestService from custom package..");
		super.stop(bootstrapLoader, args);
	}

	public String customCall(String message) {
		if(this.getState().compareTo(ServiceState.STARTED) > 0){
			return "Hello, you have just invoked a customCall from TestService with a message " + message;
		}
		else{
			log.error("Method invoked before the service is started...");
			return null;
		}
	}

}

package com.test;

import com.kousikraj.bootstrap.core.Bootstrap;
import com.kousikraj.bootstrap.services.AbstractService;
import com.kousikraj.bootstrap.services.ServiceType;

public class DummyService extends AbstractService {

	@Override
	public void init(Bootstrap bootstrapLoader, Object args) {
		log.info("Initialized DummyService from custom package..");
		super.init(bootstrapLoader, args);
	}
	
	@Override
	public void start(Bootstrap bootstrapLoader, Object args) {
		log.info("Started DummyService from custom package..");
		super.start(bootstrapLoader, args);
	}
	
	@Override
	public void stop(Bootstrap bootstrapLoader, Object args) {
		log.info("Stopped DummyService from custom package..");
		super.stop(bootstrapLoader, args);
	}
	
	@Override
	public ServiceType getType() {
		return ServiceType.ADDON;
	}
	
}

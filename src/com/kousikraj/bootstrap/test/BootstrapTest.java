package com.kousikraj.bootstrap.test;

import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.PropertyConfigurator;

//import com.kousikraj.bootstrap.configurations.ServerConfigurations;
import com.kousikraj.bootstrap.core.Bootstrap;
import com.kousikraj.bootstrap.lookups.ServiceDirectory;
import com.test.TestService;

//import com.kousikraj.bootstrap.utils.PropertiesFileLoader;

/**
 * A sample test file to test the {@link Bootstrap} implementation
 * 
 * @author KousikRaj
 * 
 */
public class BootstrapTest {

	public static void main(String[] args) {
		// This configuration will print all the log in to console
		BasicConfigurator.configure();
		// Uncomment this line if you want to push the logs to a file..
		// PropertyConfigurator.configure(PropertiesFileLoader.loadPropertiesFromFile(ServerConfigurations.LOG_PROPERTIES));
		
		System.out.println("1. Test Case\n------------------------------------------------------------");
		new BootstrapTest().normalTestCase();
		
		//System.out.println("2. Test Case with Error\n------------------------------------------------------------");
		//new BootstrapTest().errorTestCase();
		
		//System.out.println("3. Test Case with Error\n------------------------------------------------------------");
		//new BootstrapTest().errorTestCase2();
	}
	
	public void normalTestCase(){
		Bootstrap.getInstance().bootSystem();
		TestService testService =  (TestService) ServiceDirectory.getInstance().getService("TestService");
		System.out.println("****\n"+testService.customCall("Test message from Kousik Rajendran")+"\n****");
		Bootstrap.getInstance().shutDown();
	}
	
	public void errorTestCase(){
		Bootstrap.getInstance().bootSystem();
		Bootstrap.getInstance().shutDown();
		TestService testService =  (TestService) ServiceDirectory.getInstance().getService("TestService");
		System.out.println("****\n"+testService.customCall("Test message from Kousik Rajendran")+"\n****");
	}
	
	public void errorTestCase2(){
		TestService testService =  (TestService) ServiceDirectory.getInstance().getService("TestService");
		System.out.println("****\n"+testService.customCall("Test message from Kousik Rajendran")+"\n****");
		Bootstrap.getInstance().bootSystem();
		Bootstrap.getInstance().shutDown();
	}

}